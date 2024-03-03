package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.User;

public interface UserService {
	User saveUserInfo(User user);
	
	boolean checkUser(String email,String password);
}
