package com.campus.Assistant.controller;

import com.campus.Assistant.service.CampusAIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final CampusAIService aiService;

    public ChatController(CampusAIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping
    public String ask(@RequestParam String message) {
        return aiService.getSmartResponse(message);
    }
}