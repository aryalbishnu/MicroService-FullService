package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/users")
public class UserServiceController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceController.class);
	
	@PostMapping()
	public ResponseEntity<User> creatUserMicro(@RequestBody User user){
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<User>>getAllUser(){
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	//@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod="ratingHotelFallBack")
	//@Retry(name="ratingHotelService", fallbackMethod="ratingHotelFallBack")
	@RateLimiter(name="ratingHotelUserRateLimiter", fallbackMethod="ratingHotelFallBack")
	public ResponseEntity<User> getUserById(@PathVariable String userId){
		//logger.info("retry count : {}", retryCount);
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);	
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserByUserId(@PathVariable String userId){
		userService.deleteUserById(userId);
		return new ResponseEntity<String>("success full delete", HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUserByUserId(@PathVariable String userId, @RequestBody User user){
		return new ResponseEntity<User>(userService.updateUserById(userId, user), HttpStatus.OK);
	}
	
	//Reating fall back method for circuitBreaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex){
		//logger.info("fallback because service is down : ", ex.getMessage());
		ex.printStackTrace();
		User user = new User();
		user.setAbout("server fall created of dummy");
		user.setEmail("dummy@gmail.com");
		user.setName("dummy");
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}

}
