package com.example.spring.listeners;

import com.example.spring.events.ExpenseAddedEvent;
import com.example.spring.services.MetricsService;
import com.example.spring.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

import static org.mockito.Mockito.*;

class ExpenseAddedListenerTest {

    private ExpenseAddedListener expenseAddedListener;
    private NotificationService notificationService;
    private MetricsService metricsService;

    @BeforeEach
    void setUp() {
        notificationService = mock(NotificationService.class);
        metricsService = mock(MetricsService.class);
        expenseAddedListener = new ExpenseAddedListener(notificationService, metricsService);
    }

    @Test
    void testOnExpenseAdded() {
        ExpenseAddedEvent event = new ExpenseAddedEvent("Test Expense");

        // Trigger the event listener method
        expenseAddedListener.onExpenseAdded(event);

        // Verify that the logEvent method was called with the correct event
        verify(notificationService, times(1)).sendNotification("A new expense was added: Test Expense");
        verify(metricsService, times(1)).incrementExpenseCount();
    }
}
