package com.ms.userservice.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ms.userservice.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/api/v1/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
