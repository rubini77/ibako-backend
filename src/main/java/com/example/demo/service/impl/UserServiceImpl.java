package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userrepo;
	
	@Override
	public User saveUserInfo(User user) {
		
		User savedEntity = userrepo.save(user);
		
		
		return savedEntity;
	}

	@Override
	public boolean checkUser(String email,String password) {
		
		User user =userrepo.findByEmail(email);
		if(user!= null && user.getPassword().equals(password)) {
			return true;
		}
		else
			return false;
		
//		return user!=null && user.getPassword().equals(password);
		}
//		User userexists = userrepo.findByEmail(user.getEmail());
//		if(userexists == null || !userexists.getEmail().equals(user.getEmail())) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid email and password");
//		}
//		return ResponseEntity.status(HttpStatus.OK).body("Login successful") ;
//	}

}