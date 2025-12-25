package com.example.problemservice.controller;

import com.example.problemservice.model.Problem;
import com.example.problemservice.repository.ProblemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class ViewController {

    private final ProblemRepository repository;

        public ViewController(ProblemRepository repository) {
            this.repository = repository;
        }

        @GetMapping("/dashboard")
        public String showDashboard(@RequestParam(value = "search", required = false) String search, Model model) {
            List<Problem> problems;

            if (search != null && !search.isEmpty()) {
                problems = repository.findByTitleContainingIgnoreCase(search);
            } else {
                problems = repository.findAll();
            }

            model.addAttribute("problems", problems);
            model.addAttribute("searchWord", search); // Search box lo meeru type chesindhi alaage undalante
            return "index";
        }

        @PostMapping("/add")
        public String addProblem(@ModelAttribute Problem problem) {
            repository.save(problem);
            return "redirect:/ui/dashboard";
        }
    }

