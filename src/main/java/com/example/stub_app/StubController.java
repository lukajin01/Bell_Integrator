package com.example.stub_app;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class StubController {

    @GetMapping("/status")
    public String getStatus() throws InterruptedException {
        Thread.sleep(1500);
        return "{\"login\":\"Login1\",\"status\":\"ok\"}";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> body) throws InterruptedException {
        Thread.sleep(1500);
        String login = body.get("login");
        String password = body.get("password");
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.format("{\"login\":\"%s\",\"password\":\"%s\",\"date\":\"%s\"}", login, password, date);
    }
}