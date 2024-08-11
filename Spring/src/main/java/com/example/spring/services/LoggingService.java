package com.example.spring.services;

import com.example.spring.observer.Observer;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LoggingService implements Observer {

    private static final String LOG_FILE_PATH = "activity_log.txt"; // Path to the log file

    @Override
    public void update(String message) {
        // Log to the console
        System.out.println("Logging activity: " + message);

        // Log to a file
        logToFile(message);
    }

    private void logToFile(String message) {
        // Get current time
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Prepare the log message
        String logMessage = timestamp + " - " + message;

        // Write the log message to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logMessage);
            writer.newLine(); // Move to the next line for the next log
        } catch (IOException e) {
            System.err.println("Failed to write log to file: " + e.getMessage());
        }
    }
}