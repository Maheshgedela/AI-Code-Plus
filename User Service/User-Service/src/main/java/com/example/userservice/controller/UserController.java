package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repository;

        public UserController(UserRepository repository) {
            this.repository = repository;
        }

        @GetMapping
        public List<User> getUsers() {
            return repository.findAllByOrderByScoreDesc();
        }

        @GetMapping("/top")
        public List<User> getTopUsers() {
            return repository.findAllByOrderByScoreDesc().stream().limit(5).toList();
        }

        @PostMapping
        public User createUser(@RequestBody User user) {
            if (user.getScore() == 0) user.setScore(0);
            return repository.save(user);
        }


        @PutMapping("/{username}/add-points")
        public ResponseEntity<User> addPoints(@PathVariable String username, @RequestParam int points) {
            if (points <= 0) {
                return ResponseEntity.badRequest().build();
            }

            User user = repository.findByUsername(username)
                    .orElseGet(() -> {
                        // Chrome lo name change chesi submit chesinappudu database lo lekapothe ikkade create avthundi
                        User newUser = new User();
                        newUser.setUsername(username);
                        newUser.setEmail(username.toLowerCase() + "@aimentor.com");
                        newUser.setScore(0);
                        return repository.save(newUser);
                    });

            user.setScore(user.getScore() + points);
            repository.save(user);
            return ResponseEntity.ok(user);
        }

        @GetMapping("/{username}")
        public ResponseEntity<User> getUser(@PathVariable String username) {
            return repository.findByUsername(username)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{username}")
        public ResponseEntity<String> deleteUser(@PathVariable String username) {
            return repository.findByUsername(username)
                    .map(user -> {
                        repository.delete(user);
                        return ResponseEntity.ok("User " + username + " deleted successfully!");
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
    }