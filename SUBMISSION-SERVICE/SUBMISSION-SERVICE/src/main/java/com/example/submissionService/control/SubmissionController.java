package com.example.submissionService.control;

import com.example.submissionService.client.NotificationClient;
import com.example.submissionService.client.UserClient;
import com.example.submissionService.model.Submission;
import com.example.submissionService.repository.SubmissionRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    private final SubmissionRepository repository;
        private final UserClient userClient;
        private final NotificationClient notificationClient;

        public SubmissionController(SubmissionRepository repository,
                                    UserClient userClient,
                                    NotificationClient notificationClient) {
            this.repository = repository;
            this.userClient = userClient;
            this.notificationClient = notificationClient;
        }

        @PostMapping
        public Submission createSubmission(@RequestBody Submission submission) {
            submission.setSubmittedAt(LocalDateTime.now());
            Submission savedSubmission = repository.save(submission);

            if ("Correct".equalsIgnoreCase(submission.getStatus())) {
                try {
                    userClient.addPoints(submission.getUsername(), 10);
                } catch (Exception e) {
                    System.err.println("FAILED to update points: " + e.getMessage());
                }
            }

            try {
                Map<String, Object> notificationData = new HashMap<>();
                notificationData.put("username", submission.getUsername());
                notificationData.put("message", "Submission for Problem #" + submission.getProblemId() + " is " + submission.getStatus());
                notificationData.put("type", "SUBMISSION_ALERT");

                notificationClient.sendNotification(notificationData);
            } catch (Exception e) {
                System.err.println("FAILED to send notification: " + e.getMessage());
            }

            return savedSubmission;
        }

        @GetMapping("/user/{username}")
        public List<Submission> getUserHistory(@PathVariable String username) {
            return repository.findByUsernameOrderBySubmittedAtDesc(username);
        }
    }


