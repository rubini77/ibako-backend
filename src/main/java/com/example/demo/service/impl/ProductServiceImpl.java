package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import com.example.demo.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productrepo;

	@Override
	public Product addProduct(Product product) {
		Product savedEntity = productrepo.save(product);
		return savedEntity;	
	}
	

}