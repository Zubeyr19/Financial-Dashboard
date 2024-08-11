package com.example.spring.events;

import com.example.spring.events.ExpenseAddedEvent;
import com.example.spring.services.MetricsService;
import com.example.spring.services.NotificationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ExpenseAddedEventTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private MetricsService metricsService;

    @Test
    void testExpenseAddedEventHandling() {
        String expenseTitle = "Test Expense";
        ExpenseAddedEvent event = new ExpenseAddedEvent("Test Expense");

        // Publish the event
        eventPublisher.publishEvent(event);

        // Verify that the listener reacted correctly
        verify(notificationService, timeout(1000).times(1)).sendNotification("A new expense was added: " + expenseTitle);
        verify(metricsService, timeout(1000).times(1)).incrementExpenseCount();
    }
}
