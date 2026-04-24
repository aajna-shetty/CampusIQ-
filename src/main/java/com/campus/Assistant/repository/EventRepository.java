package com.campus.Assistant.repository;

import com.campus.Assistant.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends MongoRepository<Event,String> {
    List<Event> findByDateTimeAfterOrderByDateTimeAsc(LocalDateTime now);

    List<Event> findByDateTimeBefore(LocalDateTime now);
}
