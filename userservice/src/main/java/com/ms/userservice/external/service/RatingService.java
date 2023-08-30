package com.ms.userservice.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ms.userservice.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/api/v1/ratings")
    Rating createRating(Rating rating);

    @GetMapping("/api/v1/ratings/users/{userId}")
    List<Rating> geRating(@PathVariable String userId);
}
