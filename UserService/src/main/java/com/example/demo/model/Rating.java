package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	
	private String id;
	
	private String userId;
	
	private String hotelId;
	
	private String rating;
	
	private String feadback;
	
	private Hotel hotel;

}
