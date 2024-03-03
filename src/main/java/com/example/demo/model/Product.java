package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue
	private int id;
	
//    private MultipartFile imageFile;

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public MultipartFile getImageFile() {
//		return imageFile;
//	}
//
//	public void setImageFile(MultipartFile imageFile) {
//		this.imageFile = imageFile;
//	}
    
   

}
