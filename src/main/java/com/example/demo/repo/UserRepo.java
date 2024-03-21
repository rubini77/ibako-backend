package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
//    @Query(value = "SELECT * FROM user_datas WHERE email = :emailId", nativeQuery = true)
	User findByEmail(String emailId);
	boolean existsByEmail(String email);
	boolean existsByName(String name);
	
}
