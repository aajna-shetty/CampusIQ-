package com.campus.Assistant.repository;

import com.campus.Assistant.model.Timetable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TimetableRepository extends MongoRepository<Timetable,String> {
    Optional<Timetable> findByDayOfWeekIgnoreCase(String day);
}
