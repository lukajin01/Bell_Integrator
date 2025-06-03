package com.example.stub_app.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private LocalDate date;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.date = LocalDate.now();
    }

    public void setDateToNow() {
        this.date = LocalDate.now();
    }
}