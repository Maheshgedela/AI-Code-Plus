package com.example.NOTFICATION_SERVICE.controller;

import com.example.NOTFICATION_SERVICE.model.Notification;
import com.example.NOTFICATION_SERVICE.repository.NotificationRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationRepository repository;

        public NotificationController(NotificationRepository repository) {
            this.repository = repository;
        }

        @PostMapping
        public Notification sendNotification(@RequestBody Notification notification) {
            notification.setCreatedAt(LocalDateTime.now());
            return repository.save(notification);
        }

        @GetMapping("/{username}")
        public List<Notification> getNotifications(@PathVariable String username) {
            return repository.findByUsernameOrderByCreatedAtDesc(username);
        }
    }