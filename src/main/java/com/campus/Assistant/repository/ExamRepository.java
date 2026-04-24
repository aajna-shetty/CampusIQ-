package com.campus.Assistant.repository;

import com.campus.Assistant.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ExamRepository extends MongoRepository<Exam, String> {
    // Find exams happening in the next 48 hours
    List<Exam> findByExamDateBetween(LocalDateTime start, LocalDateTime end);
}