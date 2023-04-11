package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
	
	//create
	User createUser(User user);

    //get all user
	List<User>getAllUsers();
	
	//get single user by userId
	User getUserById(String userId);
	
	//update single user by userId
    User updateUserById(String userId, User user);
    
    //update single user by userId
     void deleteUserById(String userId);
		
		
	
}
