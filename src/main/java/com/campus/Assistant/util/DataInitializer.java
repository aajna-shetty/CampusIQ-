package com.campus.Assistant.util;

import com.campus.Assistant.model.*;
import com.campus.Assistant.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EventRepository eventRepo;
    private final TimetableRepository timetableRepo;
    private final LocationRepository locationRepo;
    private final ExamRepository examRepo;

    public DataInitializer(EventRepository eventRepo,
                           TimetableRepository timetableRepo,
                           LocationRepository locationRepo,
                           ExamRepository examRepo) {
        this.eventRepo = eventRepo;
        this.timetableRepo = timetableRepo;
        this.locationRepo = locationRepo;
        this.examRepo = examRepo;
    }

    @Override
    public void run(String... args) {
        examRepo.deleteAll();
        locationRepo.deleteAll();
        eventRepo.deleteAll();
        timetableRepo.deleteAll();

        LocalDateTime now = LocalDateTime.now();

        // --- 1. THE "DOOMED" DATA (Past events - Janitor will delete) ---
        Event ethnicDay = new Event();
        ethnicDay.setTitle("NHCE Ethnic Day 2026");
        ethnicDay.setDateTime(now.minusDays(2).withHour(10).withMinute(0));
        ethnicDay.setLocation("Main Campus Grounds");
        ethnicDay.setCategory("Cultural");
        eventRepo.save(ethnicDay);

        for (int i = 1; i <= 4; i++) {
            Event past = new Event();
            past.setTitle("Past Technical Session #" + i);
            past.setDateTime(now.minusDays(i + 3));
            eventRepo.save(past);
        }

        // --- 2. THE "SAFE" DATA (Future - Janitor will NOT delete) ---

        // The Exam (Highest Priority)
        Exam mathExam = new Exam();
        mathExam.setSubject("Advanced Mathematics");
        // FIXED SYNTAX BELOW:
        mathExam.setExamDate(now.plusDays(1).withHour(9).withMinute(30));
        mathExam.setLocation("Main Hall - Desk 42");
        mathExam.setInstructions("Bring Hall Ticket and Calculator.");
        examRepo.save(mathExam);

        // Initium Fest (The True Main Highlight)
        Event initium = new Event();
        initium.setTitle("INITIUM 2026: THE ANNUAL COLLEGE FEST");
        initium.setDescription("The biggest inter-collegiate mega fest at NHCE. Multi-day celebration with music and stalls.");
        initium.setDateTime(now.plusDays(4).withHour(10).withMinute(0)); // April 27
        initium.setLocation("Full Campus Grounds");
        initium.setCategory("Major Fest");
        eventRepo.save(initium);

        // ISE Hackathon (Marked as a Departmental session)
        Event hackathon = new Event();
        hackathon.setTitle("ISE Dept Coding Session");
        hackathon.setDescription("A departmental coding challenge for ISE students.");
        hackathon.setDateTime(now.plusDays(3).withHour(9).withMinute(0)); // April 26
        hackathon.setLocation("Innovation Lab");
        hackathon.setCategory("Technical Workshop");
        eventRepo.save(hackathon);

        // --- 3. CORE CAMPUS LOCATIONS ---
        Location mainHall = new Location();
        mainHall.setName("Main Hall");
        mainHall.setBlock("Administrative Block");
        locationRepo.save(mainHall);

        System.out.println("=================================================");
        System.out.println(">>> SEEDING COMPLETE: Error fixed and Initium prioritized.");
        System.out.println("=================================================");
    }
}