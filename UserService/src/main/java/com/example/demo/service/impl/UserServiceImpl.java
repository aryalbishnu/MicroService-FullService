package com.example.demo.service.impl;



import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.MyException;
import com.example.demo.externalService.HotelService;
import com.example.demo.model.Hotel;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired 
	private HotelService hotelService;
	
	

	@Override
	public User createUser(User user) {	
		user.setUserId(UUID.randomUUID().toString());
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> user = userRepository.findAll();
		for(User user1:user) {
			//fetch rating of the above user from Rating Servic
			Rating[] ratingsUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+ user1.getUserId(), Rating[].class);
			List<Rating>ratings = Arrays.stream(ratingsUser).toList();
			ratings.stream().map(rating ->{
				//fetch hotel of the above rating from hotel service use feinClient	
			Hotel hotel = hotelService.getHotelByRating(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
			}).collect(Collectors.toList());
			user1.setRatings(ratings);
		}		
		return user;
	}


	@Override
	public User getUserById(String userId){
		User user = userRepository.findById(userId).orElseThrow(() -> new MyException("this userId is not valid :" + userId));
		//fetch rating of the above user from Rating Servic
		Rating[] ratingsUser =  restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+ user.getUserId(), Rating[].class);
		 List<Rating>ratings = Arrays.stream(ratingsUser).toList();
		 ratings.stream().map(rating -> {
	     //fetch hotel of the above rating from hotel service use RestTemplate
		 //ResponseEntity<Hotel>hotelEntity =	restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
		 //Hotel hotel = hotelEntity.getBody();
		 //fetch hotel of the above rating from hotel service use feinClient
		 Hotel hotel = hotelService.getHotelByRating(rating.getHotelId());
		 rating.setHotel(hotel);
		 return rating;
		 }).collect(Collectors.toList());
		user.setRatings(ratings);
		return user;
	}

	public User updateUserById(String userId, User user) {
		User user1 =  userRepository.findById(userId).orElseThrow(()-> new MyException("this userId is not valid :"+ userId));
		user1.setAbout(user.getAbout());
		user1.setEmail(user.getEmail());
		user1.setName(user.getName());
		return userRepository.save(user1);
	}

	@Override
	public void deleteUserById(String userId) {
		User user =  userRepository.findById(userId).orElseThrow(()-> new MyException("this userId is not valid :"+ userId));		
		userRepository.delete(user);
	}

}