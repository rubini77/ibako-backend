package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CheckOut;

@Repository
public interface CheckOutRepo extends JpaRepository<CheckOut, Integer>{

}
