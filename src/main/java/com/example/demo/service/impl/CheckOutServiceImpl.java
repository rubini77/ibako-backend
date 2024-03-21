package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CheckOut;
import com.example.demo.repo.CheckOutRepo;
import com.example.demo.service.CheckOutService;

@Service
public class CheckOutServiceImpl implements CheckOutService{
	
	@Autowired
	private CheckOutRepo checkoutrepo;

	@Override
	public CheckOut addOrders(CheckOut checkout) {
		CheckOut savedEntity = checkoutrepo.save(checkout);
		return savedEntity;	
	}

}
