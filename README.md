# 🚀 AI CodePulse - Microservices Based Online Judge

**AI CodePulse** is a modern, microservices-driven online coding platform that uses Artificial Intelligence to mentor students. It doesn't just check if the code is correct; it helps complete the logic and provides real-time feedback.

---

## 🏗 Microservices Architecture
The project is built using **Spring Cloud** and follows a distributed architecture:

| Service Name | Port | Description |
| :--- | :--- | :--- |
| **Eureka Server** | `8761` | Service Registration and Discovery. |
| **API Gateway** | `8081` | Central entry point and routing. |
| **Problem Service** | `8080` | Manages coding challenges and AI Mentorship. |
| **User Service** | `8082` | Handles user profiles and scoring system. |
| **Submission Service** | `8083` | Tracks code history and triggers updates. |
| **Notification Service** | `8085` | Sends real-time alerts and feedback badges. |

---

## 🌟 Key Features
* **AI Mentorship:** Integrated with **Spring AI** to provide full code solutions and logic explanation.
* **Dynamic Leaderboard:** Real-time score tracking across the platform.
* **Submission History:** Users can track their past attempts and AI feedback.
* **Real-time Notifications:** Instant alerts for submission status using a dedicated microservice.
* **Responsive UI:** A clean, modern dashboard built with Thymeleaf and Bootstrap 5.

---

## 🛠 Tech Stack
* **Backend:** Java 17+, Spring Boot 3.x, Spring Cloud.
* **AI Integration:** Spring AI (OpenAI / Gemini).
* **Database:** MySQL (Separate databases for each service).
* **Communication:** REST & OpenFeign.
* **Frontend:** Thymeleaf, JavaScript (ES6), Bootstrap 5.

---

## 🚦 How to Run the Project

### 1. Database Setup
Create the following databases in MySQL:
- `user_db`, `problem_db`, `submission_db`, `notification_db`

### 2. Start Services (Order is Important)
1. Run **Eureka Server** (`8761`)
2. Run **API Gateway** (`8081`)
3. Run **User Service**, **Problem Service**, **Submission Service**, and **Notification Service**.

### 3. Access the Dashboard
Open your browser and navigate to:
```bash
http://localhost:8081/ui/dashboard

📸 Project Workflow
Submit Code: Student writes partial logic for a challenge.

AI Evaluation: Problem service calls Spring AI to complete the code and give feedback.

Data Sync: Submission service updates the score via User Service and triggers a notification.

Instant Alert: Notification Service updates the bell icon on the UI.

👤 Author
Name: Ganesh

Project Role: Lead Microservices Developer
### Ee File ni ela use cheyali?
1. Mee project root directory (Main folder) lo **New File** create cheyandi.
2. Daaniki **`README.md`** ani peru pettandi.
3. Paina nenu ichina content ni copy-paste chesi save cheyandi.

**Mee project ippudu oka professional product la ready aindi!** Ippudu AI correct ga full code isthunda? Leda inka emaina doubts unnaya?
Open your browser and navigate to:
```bash
http://localhost:8081/ui/dashboard
