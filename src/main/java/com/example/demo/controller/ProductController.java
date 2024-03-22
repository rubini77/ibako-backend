package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productservice; //dependency injection
	
	
	@PostMapping("/addProduct")
    public ResponseEntity<?> postProduct(@RequestBody Product product){
		productservice.addProduct(product);
		
		return ResponseEntity.status(HttpStatus.OK).body(product);
		
	}
	@GetMapping("/get")
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> datas = productservice.getProduct();
		
		return ResponseEntity.status(HttpStatus.OK).body(datas);
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		
		Product product = productservice.deleteProduct(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Data Deleted" + product);
		
	}
	
	
}
