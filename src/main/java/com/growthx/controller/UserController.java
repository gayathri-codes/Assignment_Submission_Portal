package com.growthx.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.growthx.model.Admin;
import com.growthx.model.Assignment;
import com.growthx.model.User;
import com.growthx.repository.AssignmentRepo;
import com.growthx.service.AdminService;
import com.growthx.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AssignmentRepo assignmentRepository;
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.loginUser(username, password);
        return user.isPresent() ? ResponseEntity.ok("Login Successful") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/upload")
    public ResponseEntity<Assignment> uploadAssignment(@RequestBody Assignment assignment) {
        assignment.setStatus("PENDING");
        return ResponseEntity.ok(assignmentRepository.save(assignment));
    }

    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAdmins() {
        return ResponseEntity.ok(adminService.findAll());
    }
}
