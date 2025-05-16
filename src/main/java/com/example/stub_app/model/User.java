package com.example.stub_app.model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    private String date;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDateToNow() {
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
