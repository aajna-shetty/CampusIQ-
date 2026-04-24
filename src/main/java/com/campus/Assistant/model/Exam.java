package com.campus.Assistant.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "exams")
public class Exam {
    @Id
    private String id;

    public Exam() {
    }

    public Exam(String id, String subject, LocalDateTime examDate, String location, String instructions) {
        this.id = id;
        this.subject = subject;
        this.examDate = examDate;
        this.location = location;
        this.instructions = instructions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDateTime examDate) {
        this.examDate = examDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    private String subject;
    private LocalDateTime examDate;
    private String location;     // e.g., "Main Hall", "Lab 2"
    private String instructions; // e.g., "Bring your ID card and a scientific calculator"
}