package com.example.backend.controller;

import com.example.backend.response.Stats;
import com.example.backend.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/stats")
    public ResponseEntity<Stats> getStats() {
        return ResponseEntity.ok(statsService.stats());
    }
}
