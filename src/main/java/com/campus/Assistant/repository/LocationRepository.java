package com.campus.Assistant.repository;

import com.campus.Assistant.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location, String> {
    // This allows the service to search for "Lab 5" ignoring caps
    Optional<Location> findByNameIgnoreCase(String name);
}