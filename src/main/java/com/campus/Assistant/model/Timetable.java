package com.campus.Assistant.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "timetables")
public class Timetable {
    @Id
    private String id;

    public Timetable() {
    }

    public Timetable(String id, String dayOfWeek, List<CourseSchedule> schedules) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.schedules = schedules;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<CourseSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<CourseSchedule> schedules) {
        this.schedules = schedules;
    }

    private String dayOfWeek; // e.g., "MONDAY"
    private List<CourseSchedule> schedules;
}

