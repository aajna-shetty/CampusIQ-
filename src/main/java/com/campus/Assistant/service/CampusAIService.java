package com.campus.Assistant.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class CampusAIService {

    private final CampusService campusService;
    private final RestTemplate restTemplate;

    @Value("${openrouter.api-key}")
    private String apiKey;

    @Value("${openrouter.model-id}")
    private String modelId;

    public CampusAIService(CampusService campusService) {
        this.campusService = campusService;
        this.restTemplate = new RestTemplate();
    }

    public String getSmartResponse(String userQuery) {
        String alerts = campusService.getCriticalAlerts();
        String url = "https://openrouter.ai/api/v1/chat/completions";


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("HTTP-Referer", "http://localhost:8080"); // Required by OpenRouter
        headers.set("X-Title", "Aajna Campus Assistant");
        headers.setContentType(MediaType.APPLICATION_JSON);


        Map<String, Object> body = new HashMap<>();
        body.put("model", modelId);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "You are Aajna's Assistant. Context: " + alerts));
        messages.add(Map.of("role", "user", "content", userQuery));
        body.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            List choices = (List) response.getBody().get("choices");
            Map firstChoice = (Map) choices.get(0);
            Map message = (Map) firstChoice.get("message");

            return (String) message.get("content");

        } catch (Exception e) {
            System.err.println("OpenRouter Error: " + e.getMessage());
            return "AI offline. Local Alert: " + alerts;
        }
    }
}