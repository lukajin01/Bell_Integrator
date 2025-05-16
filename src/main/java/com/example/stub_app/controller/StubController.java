package com.example.stub_app.controller;

import com.example.stub_app.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        User responseUser = new User(inputUser.getLogin(), inputUser.getPassword());
        return ResponseEntity.ok(responseUser);
    }

    private void simulateDelay() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }
}
