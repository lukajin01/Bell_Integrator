package com.example.stub_app.util;

import com.example.stub_app.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class FileWorker {

    private static final Path WRITE_FILE = Paths.get("found-users.txt");
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public void writeUser(User user) {
        try (BufferedWriter bw = Files.newBufferedWriter(
                WRITE_FILE, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            String json = MAPPER.writeValueAsString(user);
            bw.write(json);
            bw.newLine();

        } catch (IOException e) {
            System.err.println("IOException in writeUser: "
                    + e.getClass().getName() + " — " + e.getMessage());
            e.printStackTrace();
            throw new UncheckedIOException("Не удалось записать в файл: " + e.getMessage(), e);
        }
    }

    public String readRandomLine() {
        try (var is = getClass().getClassLoader().getResourceAsStream("random-lines.txt")) {
            if (is == null) {
                throw new IllegalStateException("Файл random-lines.txt не найден в ресурсах");
            }
            List<String> lines = new java.io.BufferedReader(new java.io.InputStreamReader(is)).lines().toList();
            if (lines.isEmpty()) throw new IllegalStateException("Файл пуст");

            int idx = ThreadLocalRandom.current().nextInt(lines.size());
            return lines.get(idx);

        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось прочитать файл из ресурсов", e);
        }
    }
}
