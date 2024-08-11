package com.example.spring.events;

public class ExpenseAddedEvent {
    private final String expenseTitle;

    public ExpenseAddedEvent(String expenseTitle) {
        this.expenseTitle = expenseTitle;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }
}
