package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {
	Product addProduct(Product product);
	List<Product> getProduct();
	Product deleteProduct(int id);
}
