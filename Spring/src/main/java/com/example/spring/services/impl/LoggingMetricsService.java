package com.example.spring.services.impl;

import com.example.spring.services.MetricsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingMetricsService implements MetricsService {

    private static final Logger logger = LoggerFactory.getLogger(LoggingMetricsService.class);

    private int expenseCount = 0;

    @Override
    public void recordMetric(String metricName, String value) {
        logger.info("Metric recorded: {} = {}", metricName, value);
    }

    @Override
    public void incrementExpenseCount() {
        expenseCount++;
        logger.info("Expense count incremented: {}", expenseCount);
    }
}