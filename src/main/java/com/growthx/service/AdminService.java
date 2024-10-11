package com.growthx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growthx.model.Admin;
import com.growthx.model.Assignment;
import com.growthx.repository.AdminRepo;
import com.growthx.repository.AssignmentRepo;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepository;
    @Autowired
    private AssignmentRepo assignmentRepository;

    public Admin registerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Optional<Admin> loginAdmin(String username, String password) {
        return adminRepository.findByUsername(username)
                .filter(admin -> admin.getPassword().equals(password));
    }

    public List<Assignment> viewAssignments(String admin) {
        return assignmentRepository.findByAdmin(admin);
    }

    public Assignment acceptAssignment(String assignmentId) {
        return updateAssignmentStatus(assignmentId, "ACCEPTED");
    }

    public Assignment rejectAssignment(String assignmentId) {
        return updateAssignmentStatus(assignmentId, "REJECTED");
    }

    private Assignment updateAssignmentStatus(String assignmentId, String status) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
            .orElseThrow(() -> new RuntimeException("Assignment not found"));
        assignment.setStatus(status);
        return assignmentRepository.save(assignment);
    }
    
    public List<Admin> findAll(){
    	return adminRepository.findAll();
    }
}
