package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelServiceController {
	
	@Autowired
	private HotelService hotelService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping()
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.createHotel(hotel));
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping()
	public ResponseEntity<List<Hotel>> getAllHotel(){
		return new ResponseEntity<List<Hotel>>(hotelService.getAllHotel(), HttpStatus.OK);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable String hotelId){
		return new ResponseEntity<Hotel>(hotelService.getHotelByHotelId(hotelId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<String> deleteHotelByHotelId(@PathVariable String hotelId){
		hotelService.deleteHotelByHotelId(hotelId);
		return new ResponseEntity<String>("delete success full", HttpStatus.OK);
	}
	 
	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> updateHotelByHotelId(@PathVariable String hotelId, @RequestBody Hotel hotel){
		return new ResponseEntity<Hotel>(hotelService.updateHotelByHotelId(hotelId, hotel), HttpStatus.OK);
	}
}
