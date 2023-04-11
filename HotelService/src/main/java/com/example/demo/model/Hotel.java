package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOTELS")
public class Hotel {
	
	@Id
	private String hotelId;
	
	private String name;
	
	private String location;
	
	private String about;
	

}
