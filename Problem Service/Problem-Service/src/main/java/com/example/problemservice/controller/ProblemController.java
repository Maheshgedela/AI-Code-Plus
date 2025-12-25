package com.example.problemservice.controller;

import com.example.problemservice.model.Problem;
import com.example.problemservice.repository.ProblemRepository;
import com.example.problemservice.service.AiMentorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {
    private final ProblemRepository repository;
        private final AiMentorService aiService;

        public ProblemController(ProblemRepository repository, AiMentorService aiService) {
            this.repository = repository;
            this.aiService = aiService;
        }

        @GetMapping
        public List<Problem> getAllProblems(@RequestParam(required = false) String search) {
            if (search != null && !search.isEmpty()) {
                return repository.findByTitleContainingIgnoreCase(search);
            }
            return repository.findAll();
        }

        @GetMapping("/filter")
        public List<Problem> getProblemsByDifficulty(@RequestParam String level) {
            return repository.findAll().stream()
                    .filter(p -> p.getDifficulty().equalsIgnoreCase(level))
                    .toList();
        }

        @GetMapping("/{id}")
        public Problem getProblemById(@PathVariable Long id) {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Problem not found with id: " + id));
        }

        @PostMapping
        public Problem createProblem(@RequestBody Problem problem) {
            return repository.save(problem);
        }


        @PutMapping("/{id}")
        public Problem updateProblem(@PathVariable Long id, @RequestBody Problem updatedDetails) {
            return repository.findById(id).map(problem -> {
                problem.setTitle(updatedDetails.getTitle());
                problem.setDescription(updatedDetails.getDescription());
                problem.setDifficulty(updatedDetails.getDifficulty());
                return repository.save(problem);
            }).orElseThrow(() -> new RuntimeException("Problem not found"));
        }


        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteProblem(@PathVariable Long id) {
            return repository.findById(id).map(problem -> {
                repository.delete(problem);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new RuntimeException("Problem not found"));
        }

        @PostMapping("/{id}/explain")
        public String getAiHint(@PathVariable Long id, @RequestBody String userCode) {
            Problem problem = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Problem not found"));

            return aiService.explainCode(problem.getDescription(), userCode);
        }
    }

