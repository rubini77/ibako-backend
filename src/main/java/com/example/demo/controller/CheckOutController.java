package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CheckOut;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.repo.OrderRepo;
import com.example.demo.service.CheckOutService;

@RestController
@RequestMapping("/checkout")
public class CheckOutController {
	
	@Autowired
	private CheckOutService checkoutservice;
	@Autowired
	private OrderRepo orderRepo;
	
	
	@PostMapping("/placeOrders")
	public ResponseEntity<?> addOrders(@RequestBody Order order){
		double total = 0.0;
		
		for(Item item : order.getItems()) {
			total += item.getQuantity() * item.getPrice();
		}
		order.setTotal(total);
		order.setNetTotal(total + order.getTax() + order.getDeliveryCharge());
		Order savedOrder = orderRepo.save(order);
		return ResponseEntity.status(HttpStatus.OK).body(savedOrder);
		
	}
	
	

}
