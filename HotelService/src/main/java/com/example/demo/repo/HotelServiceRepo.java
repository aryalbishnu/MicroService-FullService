package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Hotel;

public interface HotelServiceRepo extends JpaRepository<Hotel, String>{

}
