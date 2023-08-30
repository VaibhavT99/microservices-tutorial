package com.ms.userservice.service.implementation;

// import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import com.ms.userservice.entities.Hotel;
import com.ms.userservice.entities.Rating;
import com.ms.userservice.entities.User;
import com.ms.userservice.exceptions.ResourceNotFoundException;
import com.ms.userservice.external.service.HotelService;
import com.ms.userservice.external.service.RatingService;
import com.ms.userservice.repositories.UserRepository;
import com.ms.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given ID: " + userId + " not found."));

        // ----- Rest Template Method -----
        // Rating[] ratings = restTemplate
        //         .getForObject("http://RATING-SERVICE/api/v1/ratings/users/" + user.getUserId(), Rating[].class);
        //List<Rating> userRatings = Arrays.stream(ratings).toList();

        List<Rating> userRatings = ratingService.geRating(user.getUserId());

        logger.info("{} \n", userRatings);

        List<Rating> ratingList = userRatings.stream().map(rating -> {
            // ----- Rest Template Method -----
            // ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/api/v1/hotels/" + rating.getHotelId(), Hotel.class);
            // Hotel hotel = hotelEntity.getBody();
            // logger.info("response status code: ", hotelEntity.getStatusCode());

            //----- Feign Client Method -----
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(userRatings);
        user.setRatings(ratingList);

        return user;
    }
}
