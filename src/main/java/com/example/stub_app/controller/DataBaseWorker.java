package com.example.stub_app.controller;

import com.example.stub_app.exception.UserNotFoundException;
import com.example.stub_app.model.User;

import java.sql.*;

public class DataBaseWorker {

    private static final String URL = "jdbc:postgresql://postgres:5432/mydb";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public User findUserByLogin(String login) {
        User user = null;
        String sql = "SELECT u.login, u.password, p.email, u.date " +
                "FROM users u " +
                "JOIN profiles p ON u.login = p.login " +
                "WHERE u.login = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getDate("date").toLocalDate()
                    );
                }
                else {
                    throw new UserNotFoundException(login);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при выполнении SELECT: " + e.getMessage());
        }
        return user;
    }

    public int insertUser(User user){
        String sql = "INSERT INTO users (login, password, date) VALUES (?, ?, CURRENT_DATE);\n" +
                "INSERT INTO profiles (login, email) VALUES (?, ?);";

        int totalRowsAffected = 0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getEmail());

            boolean hasMoreResults = ps.execute();
            int currentRows = ps.getUpdateCount();
            totalRowsAffected += (currentRows != -1) ? currentRows : 0;

            while (ps.getMoreResults()) {
                currentRows = ps.getUpdateCount();
                totalRowsAffected += (currentRows != -1) ? currentRows : 0;
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при вставке пользователя: " + e.getMessage());
        }

        return totalRowsAffected;
    }
}