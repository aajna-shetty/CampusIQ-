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
    @GetMapping("/hi")
    public String hi() {
        return "Hello from CampusIQ via Jenkins CI/CD!";
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello Version 2";
    }
    @GetMapping("/alerts")
    public String getAlerts() {
        return campusService.getCriticalAlerts();
    }
    @GetMapping("/next-event")
    public Event getNextEvent() {
        return campusService.getNextUpcomingEvent();
    }


    @GetMapping("/timetable/{day}")
    public Timetable getTimetable(@PathVariable String day) {
        return campusService.getDailyTimetable(day);
    }

    @GetMapping("/locate")
    public String locate(@RequestParam String query) {
        return campusService.resolveLocation(query);
    }
}