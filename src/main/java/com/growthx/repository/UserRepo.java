package com.growthx.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.growthx.model.User;

public interface UserRepo extends MongoRepository<User, String>{
	Optional<User> findByUsername(String username);
	User findByEmail(String email);
}
