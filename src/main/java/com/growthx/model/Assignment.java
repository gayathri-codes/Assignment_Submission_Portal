package com.growthx.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "assignments")
@Data
public class Assignment {
    @Id
    private String id;
    private String userId;
    private String task;
    private String admin;
    private String status; // PENDING, ACCEPTED, REJECTED
    private LocalDateTime submissionTime = LocalDateTime.now();

}