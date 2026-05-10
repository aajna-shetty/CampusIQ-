package com.campus.Assistant.service;

import com.campus.Assistant.model.*;
import com.campus.Assistant.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampusService {

    private static final Logger log = LoggerFactory.getLogger(CampusService.class);

    private final EventRepository eventRepo;
    private final TimetableRepository timetableRepo;
    private final LocationRepository locationRepo;
    private final ExamRepository examRepo;

    public CampusService(EventRepository eventRepo,
                         TimetableRepository timetableRepo,
                         LocationRepository locationRepo,
                         ExamRepository examRepo) {
        this.eventRepo = eventRepo;
        this.timetableRepo = timetableRepo;
        this.locationRepo = locationRepo;
        this.examRepo = examRepo;
    }
    public Event getNextUpcomingEvent() {
        return eventRepo.findAll().stream()
                .filter(e -> e.getDateTime().isAfter(LocalDateTime.now()))
                .sorted((a, b) -> {
                    boolean aMajor = "Major Fest".equalsIgnoreCase(a.getCategory());
                    boolean bMajor = "Major Fest".equalsIgnoreCase(b.getCategory());

                    if (a.isUrgent() && !b.isUrgent()) return -1;
                    if (!a.isUrgent() && b.isUrgent()) return 1;

                    if (aMajor && !bMajor) return -1;
                    if (!aMajor && bMajor) return 1;

                    return a.getDateTime().compareTo(b.getDateTime());
                })
                .findFirst()
                .orElse(null);
    }


    public Timetable getDailyTimetable(String day) {
        return timetableRepo.findByDayOfWeekIgnoreCase(day).orElse(null);
    }

    // 🔹 Location resolver
    public String resolveLocation(String query) {
        return locationRepo.findByNameIgnoreCase(query)
                .map(loc -> String.format("%s is in %s, %s, Room %s.",
                        loc.getName(), loc.getBlock(), loc.getFloor(), loc.getRoomNumber()))
                .orElse("Location '" + query + "' not found.");
    }


    @Scheduled(cron = "0 */5 * * * *")
    public void cleanupExpiredEvents() {
        LocalDateTime now = LocalDateTime.now();
        List<Event> expiredEvents = eventRepo.findByDateTimeBefore(now);

        if (!expiredEvents.isEmpty()) {
            eventRepo.deleteAll(expiredEvents);
            log.info("🧹 JANITOR: Deleted {} expired events", expiredEvents.size());
        }
    }


    public String getCriticalAlerts() {
        StringBuilder alerts = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();


        List<Exam> exams = examRepo.findAll();
        for (Exam e : exams) {
            alerts.append(String.format("[CRITICAL EXAM] %s on %s at %s. Instructions: %s. ",
                    e.getSubject(),
                    e.getExamDate().toLocalDate(),
                    e.getLocation(),
                    e.getInstructions()));
        }


        List<Event> allEvents = eventRepo.findAll().stream()
                .filter(e -> e.getDateTime().isAfter(now))
                .collect(Collectors.toList());


        markUrgentEvents(allEvents);


        allEvents.sort((a, b) -> {
            if (a.isUrgent() && !b.isUrgent()) return -1;
            if (!a.isUrgent() && b.isUrgent()) return 1;

            boolean aMajor = "Major Fest".equalsIgnoreCase(a.getCategory());
            boolean bMajor = "Major Fest".equalsIgnoreCase(b.getCategory());

            if (aMajor && !bMajor) return -1;
            if (!aMajor && bMajor) return 1;

            return a.getDateTime().compareTo(b.getDateTime());
        });


        String eventContext = allEvents.stream()
                .map(ev -> String.format("[%s%s] %s on %s. Details: %s",
                        ev.isUrgent() ? "URGENT - " : "",
                        ev.getCategory().toUpperCase(),
                        ev.getTitle(),
                        ev.getDateTime().toLocalDate(),
                        ev.getDescription()))
                .collect(Collectors.joining(". "));

        if (!eventContext.isEmpty()) {
            alerts.append(" UPCOMING EVENTS: ").append(eventContext);
        }

        return alerts.length() > 0 ? alerts.toString() : "No critical alerts.";
    }


    private void markUrgentEvents(List<Event> events) {
        LocalDateTime now = LocalDateTime.now();

        for (Event e : events) {
            if (e.getDateTime().isBefore(now.plusHours(24))) {
                e.setUrgent(true);
            } else {
                e.setUrgent(false);
            }
        }
    }
}