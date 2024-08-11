package com.example.spring.services;

public interface MetricsService {
    void recordMetric(String metricName, String value);

    void incrementExpenseCount();
}
