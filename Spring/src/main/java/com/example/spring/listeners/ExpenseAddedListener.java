package com.example.spring.listeners;

import com.example.spring.events.ExpenseAddedEvent;
import com.example.spring.services.MetricsService;
import com.example.spring.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Component
public class ExpenseAddedListener {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseAddedListener.class);

    private final NotificationService notificationService;
    private final MetricsService metricsService;

    @Autowired
    public ExpenseAddedListener(NotificationService notificationService, MetricsService metricsService) {
        this.notificationService = notificationService;
        this.metricsService = metricsService;
    }

    @EventListener
    public void onExpenseAdded(ExpenseAddedEvent event) {
        logEvent(event);
        sendNotification(event);
        updateMetrics();
    }

    private void logEvent(ExpenseAddedEvent event) {
        logger.info("Expense added event received: {}", event.getExpenseTitle());
    }

    private void sendNotification(ExpenseAddedEvent event) {
        String message = "A new expense was added: " + event.getExpenseTitle();
        notificationService.sendNotification(message);
        logger.debug("Notification sent for expense: {}", event.getExpenseTitle());
    }

    private void updateMetrics() {
        metricsService.incrementExpenseCount();
        logger.debug("Metrics updated for expense addition.");
    }
}
