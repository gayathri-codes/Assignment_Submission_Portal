package com.growthx.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.growthx.model.Assignment;

public interface AssignmentRepo extends MongoRepository<Assignment, String>{
	List<Assignment> findByAdmin(String admin);
}
