package com.campus.Assistant.controller;

import com.campus.Assistant.model.Event;
import com.campus.Assistant.model.Timetable;
import com.campus.Assistant.service.CampusService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/campus")
public class CampusController {

    private final CampusService campusService;

    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    // --- Added: Get critical alerts for exams and urgent events ---
    @GetMapping("/alerts")
    public String getAlerts() {
        return campusService.getCriticalAlerts();
    }

    // Tool: Get the next upcoming event (includes priority status)
    @GetMapping("/next-event")
    public Event getNextEvent() {
        return campusService.getNextUpcomingEvent();
    }

    // Tool: Get timetable based on the day
    @GetMapping("/timetable/{day}")
    public Timetable getTimetable(@PathVariable String day) {
        return campusService.getDailyTimetable(day);
    }

    // Tool: Find location coordinates/details
    @GetMapping("/locate")
    public String locate(@RequestParam String query) {
        return campusService.resolveLocation(query);
    }
}