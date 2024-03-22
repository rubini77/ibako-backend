package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
	private String name;
	private double price;
	private String image;
	private String time;	
	private MultipartFile file;
	
	public ProductDto() {};
	
	public ProductDto(String name, double price, String image, String time, MultipartFile file) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.time = time;
		this.file = file;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
