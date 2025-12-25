package com.example.problemservice.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiMentorService {
    private final ChatClient chatClient;

        public AiMentorService(ChatClient.Builder builder) {
            this.chatClient = builder
                    .defaultSystem("""
                You are a Senior Java Developer. Your only job is to complete the student's code.
                Do not give me back my instructions. Do not ask me to format.
                Just give the final working code and the status.
                """)
                    .build();
        }

        public String explainCode(String problemDesc, String userCode) {
            String inputCode = (userCode == null || userCode.trim().isEmpty()) ? "No code provided by student." : userCode;

            try {
                return chatClient.prompt()
                        .user(u -> u.text("""
                    Direct Order: Complete the following Java program.
                    
                    PROBLEM TO SOLVE: 
                    {desc}
                    
                    STUDENT'S EXISTING CODE: 
                    {code}

                    REQUIRED OUTPUT FORMAT (Stick to this exactly):
                    
                    STATUS: [Correct]
                    
                    FEEDBACK: [Briefly explain what the code does]

                    FULL COMPLETED JAVA CODE:
                    ```java
                    [Write the entire working java class here]
                    ```
                    """)
                                .param("desc", problemDesc)
                                .param("code", inputCode))
                        .call()
                        .content();
            } catch (Exception e) {
                return "STATUS: Error\nFEEDBACK: AI service error.";
            }
        }
    }


