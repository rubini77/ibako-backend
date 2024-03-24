package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Item;
import com.example.demo.model.Product;
import com.example.demo.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemservice;
	
	@GetMapping("/getitem")
	public ResponseEntity<List<Item>> getItem(){
		List<Item> datas = itemservice.getItem();
		
		return ResponseEntity.status(HttpStatus.OK).body(datas);
		
	}

}
