package com.campus.Assistant.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String title;

    public Event() {
    }

    public Event(String id, String title, String description, LocalDateTime dateTime, String location, String category, boolean isUrgent) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.category = category;
        this.isUrgent = isUrgent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }

    private String description;
    private LocalDateTime dateTime;
    private String location;
    private String category;


    @Transient
    private boolean isUrgent;
}
