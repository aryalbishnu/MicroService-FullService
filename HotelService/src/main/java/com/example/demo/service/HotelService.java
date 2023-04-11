package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Hotel;

public interface HotelService {
	
	// create hotel
    Hotel createHotel(Hotel hotel);
    
    // get all hotel
    List<Hotel>getAllHotel();
    
    // get single hotel
    Hotel getHotelByHotelId(String hotelId);
    
    // delete hotel
    void deleteHotelByHotelId(String hotelId);
    
    // update hotel
    Hotel updateHotelByHotelId(String hotelId, Hotel hotel);

}
