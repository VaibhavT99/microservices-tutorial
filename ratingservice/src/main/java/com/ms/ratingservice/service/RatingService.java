package com.ms.ratingservice.service;

import java.util.List;

import com.ms.ratingservice.entities.Rating;

public interface RatingService {
    Rating createRating(Rating rating);
    List<Rating> getRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
