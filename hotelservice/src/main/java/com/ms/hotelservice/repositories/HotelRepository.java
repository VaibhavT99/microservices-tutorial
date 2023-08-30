package com.ms.hotelservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.hotelservice.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
    
}
