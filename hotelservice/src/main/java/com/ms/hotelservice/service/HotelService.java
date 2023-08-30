package com.ms.hotelservice.service;

import java.util.List;

import com.ms.hotelservice.entities.Hotel;

public interface HotelService {
    Hotel creaHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotel(String id);
}
