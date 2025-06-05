package com.example.stub_app.controller;

import com.example.stub_app.exception.UserNotFoundException;
import com.example.stub_app.model.User;
import com.example.stub_app.util.FileWorker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class StubController {

    private final DataBaseWorker dbWorker;
    private final FileWorker fileWorker;

    @Autowired
    public StubController(DataBaseWorker dbWorker, FileWorker fileWorker) {
        this.dbWorker = dbWorker;
        this.fileWorker = fileWorker;
    }

    @GetMapping("/status")
    public ResponseEntity<?> getStatus(@RequestParam String login) {
        simulateDelay();
        try {
            User user = dbWorker.findUserByLogin(login);
            fileWorker.writeUser(user);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error: " + ex.getMessage());
        }
    }

    @GetMapping("/random")
    public ResponseEntity<String> getRandom() {
        simulateDelay();
        String randomJson = fileWorker.readRandomLine();
        return ResponseEntity.ok(randomJson);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User inputUser) {
        simulateDelay();
        try {
            int result = dbWorker.insertUser(inputUser);
            return ResponseEntity.ok(inputUser);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error: " + e.getMessage());
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