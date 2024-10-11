package com.growthx.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.growthx.model.Admin;
import com.growthx.model.Assignment;
import com.growthx.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.registerAdmin(admin));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestParam String username, @RequestParam String password) {
        Optional<Admin> admin = adminService.loginAdmin(username, password);
        return admin.isPresent() ? ResponseEntity.ok("Login Successful") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> viewAssignments(@RequestParam String admin) {
        return ResponseEntity.ok(adminService.viewAssignments(admin));
    }

    @PostMapping("/assignments/{id}/accept")
    public ResponseEntity<Assignment> acceptAssignment(@PathVariable String id) {
        return ResponseEntity.ok(adminService.acceptAssignment(id));
    }

    @PostMapping("/assignments/{id}/reject")
    public ResponseEntity<Assignment> rejectAssignment(@PathVariable String id) {
        return ResponseEntity.ok(adminService.rejectAssignment(id));
    }
}
