package com.ms.userservice.controller;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.userservice.entities.User;
import com.ms.userservice.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newuser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newuser);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "user-hotel-rating-breaker", fallbackMethod = "userHotelRatingFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    // Fallback method for circuit breaker
    public ResponseEntity<User> userHotelRatingFallback(String userId, Exception e) {
        logger.info("Fallback executed. Service is down: ", e.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("Service is down. Dummy user created.")
                .userId("42069")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
