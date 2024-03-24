package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.repo.ItemRepo;
import com.example.demo.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepo itemrepo;
	
	@Override
	public List<Item> getItem() {
		return itemrepo.findAll();
	}

}
