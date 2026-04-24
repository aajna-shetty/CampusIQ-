package com.campus.Assistant.model;

public class CourseSchedule {
    private String subject;
    private String time;
    private String room;

    public CourseSchedule() {}

    public CourseSchedule(String subject, String time, String room) {
        this.subject = subject;
        this.time = time;
        this.room = room;
    }

    // Standard Getters and Setters
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
}