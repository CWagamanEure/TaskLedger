# 🧾 TaskLedger – Blockchain-Based Task Manager

This project is a blockchain-powered task manager built with **Java**, **Spring Boot**, and **Jackson**, designed to explore how immutable logging, REST APIs, and multithreaded crawlers can intersect in a practical software application.

> “Blockchain isn't just about crypto—it’s about **trust** through **transparency**.”

---

## 🧠 Project Summary

- Built a REST API task tracker with **Spring Boot**
- Used a custom **blockchain** to immutably log each completed task
- Persisted the full blockchain ledger to `blockchain.json`
- Supported multithreaded crawling of productivity articles from online sources
- Enabled chain validation to detect tampering on restart
- Designed to demonstrate practical Java skills using **real-world systems thinking**

---

## ⚙️ Tech Stack

| Tool        | Role                                        |
|-------------|---------------------------------------------|
| Java 17     | Core language                               |
| Spring Boot | REST API + project framework                |
| Jackson     | JSON serialization                          |
| Maven       | Build tool & dependency manager             |
| CURL / Postman | API interaction                          |

---

## 🔧 Features

- `POST /tasks` – Add a new task
- `PUT /tasks/{id}/complete` – Mark a task complete and write to the blockchain
- `GET /tasks` – View all tasks
- `GET /tasks/chain` – View blockchain history
- `GET /tasks/validate` – Verify chain integrity
- `GET /tasks/crawl` – Run a background web crawler

---

## 🧪 Example Usage

### ➕ Add a Task

```bash
curl -X POST http://localhost:8080/tasks \
-H "Content-Type: application/json" \
-d '{"title":"Finish Resume", "completed":false}'
```

### ✅ Complete a Task

```bash
curl -X PUT http://localhost:8080/tasks/1/complete
```

### 🔎 Validate the Blockchain

```bash
curl http://localhost:8080/tasks/validate
```

---

## 📂 Project Structure

```
TaskLedger/
├── src/
│   └── main/java/com/taskledger/
│       ├── model/Task.java
│       ├── blockchain/Block.java, Blockchain.java
│       ├── controller/TaskController.java
│       ├── crawler/ProductivityCrawler.java
│       └── util/HashUtil.java
├── blockchain.json
├── pom.xml
└── README.md
```

---

## 🧩 Future Improvements

- 🔐 Add JWT-based user auth
- 📊 Add Swagger UI for API docs
- 🌐 Add frontend dashboard (React or Thymeleaf)
- 💾 Support multiple users and public/private task chains

---
