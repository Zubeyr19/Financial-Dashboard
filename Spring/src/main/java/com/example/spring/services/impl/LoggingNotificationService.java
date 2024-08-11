package com.example.spring.services.impl;

import com.example.spring.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(LoggingNotificationService.class);

    @Override
    public void sendNotification(String message) {
        logger.info("Notification: {}", message);
    }
}
