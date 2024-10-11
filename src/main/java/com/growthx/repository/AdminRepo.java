package com.growthx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.growthx.model.Admin;

public interface AdminRepo extends MongoRepository<Admin, String>{
	Optional<Admin> findByUsername(String username);

	List<Admin> findAll();
	
	Admin findByEmail(String email);
}
