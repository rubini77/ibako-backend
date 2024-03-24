package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;

@RestController
@RequestMapping("/product")
public class FileController {
	@Autowired
	private Environment env;

	@Autowired
	private ProductRepo productrepo;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadProduct(@ModelAttribute ProductDto productdto) {
		if (productdto.getFile().isEmpty()) {
			return new ResponseEntity<>("Please select a file!", HttpStatus.OK);
		}
		try {

			byte[] bytes = productdto.getFile().getBytes();

			UUID uuid = UUID.randomUUID();
			String uploadsLocation = env.getProperty("resource.uploads");
//			String uploadLocation = "/springboot/ibako-backend/products_demo_bk_v1/src/main/resources/uploads";
			String fileLocation = uploadsLocation + uuid + productdto.getFile().getOriginalFilename();
			Path path = Paths.get(fileLocation);
			Files.write(path, bytes);
			File file = new File(fileLocation);
			
			File imageFile = new File(fileLocation);
			Product product = new Product();
			product.setName(productdto.getName());
			product.setPrice(productdto.getPrice());
			product.setTime(productdto.getTime());
			product.setImage(imageFile.getName());
			
//			product.setName(productdto.getName());
//			product.setDescription(productdto.getDescription());
//			product.setPrice(productdto.getPrice());
//			product.setCategory(productdto.getCategory());
//			product.setImage(imageFile.getName());
//			
			Product savedEntity = productrepo.save(product);
			
			return ResponseEntity.status(HttpStatus.OK).body(product);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CREATED).body(e.getMessage());

		}
	}
	
	@PutMapping("UpdateProduct/{id}")
	public ResponseEntity<?> updateProducts(@PathVariable int id,@ModelAttribute ProductDto productdto){
		 try {
		        if (productdto.getFile() == null || productdto.getFile().isEmpty()) {
		            return ResponseEntity.badRequest().body("Please select a file!");
		        }

		        // Handle file upload
		        MultipartFile file = productdto.getFile();
		        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		        String uploadDir = env.getProperty("resource.uploads");
		        Path uploadPath = Paths.get(uploadDir);
		        if (!Files.exists(uploadPath)) {
		            Files.createDirectories(uploadPath);
		        }
		        try (InputStream inputStream = file.getInputStream()) {
		            Path filePath = uploadPath.resolve(fileName);
		            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		        } catch (IOException e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file!");
		        }

		        // Update product details
		        Optional<Product> optionalProduct = productrepo.findById(id);
		        if (optionalProduct.isPresent()) {
		            Product product = optionalProduct.get();
		            product.setName(productdto.getName());
		            product.setPrice(productdto.getPrice());
		            product.setTime(productdto.getTime());
		            product.setImage(fileName); // Save the filename to the database
		            productrepo.save(product);
		            return ResponseEntity.ok(product);
		        } else {
		            return ResponseEntity.notFound().build();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
		    }
		
//		try {		
//		if (productdto.getFile().isEmpty()) {
//			return new ResponseEntity<>("Please select a file!", HttpStatus.OK);
//		}
//		 
//	            byte bytes[] = productdto.getFile().getBytes();
//	            UUID uuid = UUID.randomUUID();
//	            String uploadsLocation = env.getProperty("resource.uploads");
////	            String fileLocation = uploadsLocation + uuid + productdto.getFile().getOriginalFilename();
////				Path path = Paths.get(fileLocation);
////				Files.write(path, bytes);
////	            String uploadLocation = "D://springboot/Backend-Project/src/main/resources/uploads/";
//	            String fileLocation = uploadsLocation + uuid + productdto.getFile().getOriginalFilename();
////	            String fileLocation = uploadsLocation+url;
//	            Path path = Paths.get(fileLocation);
//	            Path p = Files.write(path, bytes);
//				File file = new File(fileLocation);
//				
//				File imageFile = new File(fileLocation);
//				
//	            
//	            Product product =  productrepo.findById(id).get();
//	            product.setName(productdto.getName());
//	            product.setPrice(productdto.getPrice());
//	            product.setTime(productdto.getTime());
//	            product.setImage(imageFile.getName());
//	            Product savedEntity = productrepo.save(product);
//	            return ResponseEntity.status(HttpStatus.OK).body(savedEntity);
//		 }
//		 catch (Exception e) {
//	            e.printStackTrace();
//	            return ResponseEntity.status(HttpStatus.CREATED).body("erroe occurs");
//	        }
	}
	@PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @ModelAttribute ProductDto productDto, @RequestParam(name = "image", required = false) MultipartFile image) {
        try {
            Product existingProduct = productrepo.findById(id).orElse(null);
            if (existingProduct == null) {
                return ResponseEntity.notFound().build();
            }

            existingProduct.setName(productDto.getName());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setTime(productDto.getTime());

            if (image != null && !image.isEmpty()) {
                byte[] bytes = image.getBytes();
                String uploadLocation = "D://springboot/Backend-Project/src/main/resources/uploads/";
                UUID uuid = UUID.randomUUID();
                String url = uuid + image.getOriginalFilename();
                String fileLocation = uploadLocation + url;
                Path path = Paths.get(fileLocation);
                Files.write(path, bytes);
                existingProduct.setImage(url);
            }

            Product updatedProduct = productrepo.save(existingProduct);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating product.");
        }
    }

}
