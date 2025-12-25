package com.example.submissionService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Submission {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String username;
        private Long problemId;

        @Column(columnDefinition = "TEXT")
        private String code;

        private String status; // Correct / Incorrect

        @Column(columnDefinition = "TEXT")
        private String aiFeedback;

        private LocalDateTime submittedAt;
    }

