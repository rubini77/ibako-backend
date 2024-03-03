package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userservice; //dependency injection
	
	
	@PostMapping("/saveuser")
	public ResponseEntity<?> saveUserInfo(@RequestBody User user){
	    	userservice.saveUserInfo(user);
	    	return ResponseEntity.status(HttpStatus.OK).body(user);
	    }
	
	@PostMapping("/checkuser")
	public ResponseEntity<Boolean> checkUser(@RequestBody UserDto userdto){
		System.out.println(userdto.getEmail() + " " + userdto.getPassword());
		
		boolean success=userservice.checkUser(userdto.getEmail(),userdto.getPassword());
		return ResponseEntity.status(HttpStatus.OK).body(success);
//		ResponseEntity<?> userexists =userservice.checkUser(user);
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userexists);
	}

}
