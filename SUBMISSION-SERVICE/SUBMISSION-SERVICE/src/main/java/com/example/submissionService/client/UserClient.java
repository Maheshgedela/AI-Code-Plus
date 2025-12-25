package com.example.submissionService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")

public interface UserClient {
    @PutMapping("/api/users/{username}/add-points")
        void addPoints(@PathVariable String username, @RequestParam int points);
    }

