# 🚀 CampusIQ

**AI-Powered Smart Campus Assistant with RAG-Inspired Intelligence & CI/CD**

---

## 🧠 Overview

**CampusIQ** is an AI-powered campus assistant that provides real-time information about exams, events, timetables, and locations.
It combines structured campus data with AI-generated responses using a **Retrieval-Augmented Generation (RAG)-inspired approach**.

The system is fully containerized and automated using Docker and Jenkins, making it production-ready and cloud-deployable.

---

## ✨ Features

* 📢 **Smart Alerts** – Real-time updates for exams and events
* 🤖 **AI Chat Assistant** – Context-aware responses using OpenRouter
* 🧠 **RAG-Inspired Intelligence** – Injects live campus data into AI prompts
* 📅 **Timetable Management** – Fetch schedules by day
* 📍 **Location Finder** – Resolve campus locations instantly
* ⚡ **Priority Logic** – Major fests prioritized over regular events
* 🔄 **Scheduled Cleanup** – Removes expired events automatically
* 🚀 **CI/CD Pipeline** – Automated build & deployment using Jenkins

---

## 🧠 RAG-Inspired Architecture

CampusIQ follows a **lightweight RAG approach**:

```id="ragflow1"
User Query → Retrieve Data (MongoDB) → Inject Context → AI Generates Response
```

### 🔍 How it works

* Retrieves **real-time campus alerts** from MongoDB
* Injects this data into the AI prompt as **context**
* AI generates accurate, context-aware responses

### ⚠️ Note

This is a **RAG-inspired system**, not full RAG:

* ❌ No vector database
* ❌ No embeddings
* ✔ Structured data retrieval + prompt augmentation

---

## 🏗️ Tech Stack

* **Backend:** Java, Spring Boot
* **Database:** MongoDB
* **AI Integration:** OpenRouter API
* **DevOps:** Docker, Docker Compose, Jenkins
* **Version Control:** Git & GitHub

---

## ⚙️ System Architecture

```id="arch1"
User → REST API → Service Layer → MongoDB
                 ↓
               AI Service → OpenRouter
```

---

## 🐳 Run Locally (Docker)

### 🔹 Prerequisites

* Docker
* Docker Compose

### 🔹 Run

```bash id="run1"
docker-compose up --build
```

### 🔹 Access

```id="run2"
http://localhost:8081/api/v1/campus/alerts
```

---

## 🔌 API Endpoints

### 📢 Alerts

```id="api1"
GET /api/v1/campus/alerts
```

### 📅 Next Event

```id="api2"
GET /api/v1/campus/next-event
```

### 🗓️ Timetable

```id="api3"
GET /api/v1/campus/timetable/{day}
```

### 📍 Location

```id="api4"
GET /api/v1/campus/locate?query=Lab 5
```

### 🤖 AI Chat

```id="api5"
GET /api/v1/chat?message=your_query
```

---

## 🔐 Environment Variables

```id="env1"
SPRING_DATA_MONGODB_URI=your_mongodb_uri
openrouter.api-key=your_api_key
openrouter.model-id=your_model
```

---

## 🚀 CI/CD Pipeline (Jenkins)

Automates:

1. Maven build
2. Docker image creation
3. Container deployment
4. Health verification

---

## 🌍 Cloud Deployment

* Deployed via Docker on cloud platforms (Render/AWS)
* Uses MongoDB Atlas for database
* Environment-based configuration

---

## 🎯 Key Highlights

* AI + real-time data integration
* RAG-inspired architecture
* Fully automated CI/CD pipeline
* Containerized and cloud-ready system

---

## 👩‍💻 Author

**Aajna Shetty**

---

## ⭐ Future Enhancements

* Full RAG (embeddings + vector DB)
* Mobile application
* Notifications system
* Personalized recommendations

---

