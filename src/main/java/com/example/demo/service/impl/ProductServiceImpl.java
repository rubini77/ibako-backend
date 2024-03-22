package com.example.demo.service.impl;

import java.util.List;

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

	@Override
	public List<Product> getProduct() {
		
		return productrepo.findAll();
	}

	@Override
	public Product deleteProduct(int id) {
		Product product = productrepo.findById(id).get();
		if(product!=null)
		productrepo.delete(product);
		
		return product;
	}
	

}