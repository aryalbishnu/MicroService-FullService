package com.example.demo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.repo.HotelServiceRepo;
import com.example.demo.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelServiceRepo hotelServiceRepo;

	@Override
	public Hotel createHotel(Hotel hotel) {
		hotel.setHotelId(UUID.randomUUID().toString());
		return hotelServiceRepo.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelServiceRepo.findAll();
	}

	@Override
	public Hotel getHotelByHotelId(String hotelId) {
		return hotelServiceRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("hotel id is not valid" + hotelId));
	}

	@Override
	public void deleteHotelByHotelId(String hotelId) {
	 Hotel hotel = hotelServiceRepo.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("hotel id is not valie" + hotelId));
	   hotelServiceRepo.delete(hotel);
	}

	@Override
	public Hotel updateHotelByHotelId(String hotelId, Hotel hotel) {
	  Hotel hotel1 = hotelServiceRepo.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("hotel id is not valid" + hotelId));
	  hotel1.setAbout(hotel.getAbout());
	  hotel1.setLocation(hotel.getLocation());
	  hotel1.setName(hotel.getName());
		return hotelServiceRepo.save(hotel1);
	}

}
