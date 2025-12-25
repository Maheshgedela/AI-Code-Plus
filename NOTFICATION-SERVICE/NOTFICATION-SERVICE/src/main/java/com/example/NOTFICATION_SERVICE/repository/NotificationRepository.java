package com.example.NOTFICATION_SERVICE.repository;

import com.example.NOTFICATION_SERVICE.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUsernameOrderByCreatedAtDesc(String username);
}
