package com.example.NOTFICATION_SERVICE.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String message;
        private String type; // e.g., "SCORE_UPDATE", "SUBMISSION"
        private LocalDateTime createdAt;
    }

