package com.example.stub_app.controller;

import com.example.stub_app.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class StubController {

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        simulateDelay();
        return ResponseEntity.ok("{\"login\":\"Login1\",\"status\":\"ok\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User inputUser) {
        simulateDelay();
        return ResponseEntity.ok(inputUser);
    }

    private void simulateDelay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }
}
