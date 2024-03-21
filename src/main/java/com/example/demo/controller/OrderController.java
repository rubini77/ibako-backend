package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Order;
import com.example.demo.repo.OrderRepo;

@RestController
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderRepo orderRepo;
	@GetMapping("/get/{orderId}")
	public ResponseEntity<?> getOrders(@PathVariable int orderId){
		Order order = orderRepo.findById(orderId).get();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(order);			
		
	}

}
