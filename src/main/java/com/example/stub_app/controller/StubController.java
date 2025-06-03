package com.example.stub_app.controller;

import com.example.stub_app.model.User;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class StubController {

    private final DataBaseWorker dbWorker = new DataBaseWorker();

    @GetMapping("/status")
    public ResponseEntity<?> getStatus(@RequestParam String login) {
        simulateDelay();
        User user = dbWorker.findUserByLogin(login);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User with login \"" + login + "\" not found");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User inputUser) {
        simulateDelay();
        try {
            int result = dbWorker.insertUser(inputUser);
            if (result > 0) {
                return ResponseEntity.ok(inputUser);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Insert failed");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        }
    }

    private void simulateDelay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}