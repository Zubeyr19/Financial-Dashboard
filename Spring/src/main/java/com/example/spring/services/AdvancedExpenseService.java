package com.example.spring.services;

import com.example.spring.model.Expense;
import com.example.spring.observer.Observer;
import com.example.spring.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvancedExpenseService implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public void addExpense(Expense expense) {
        // Advanced processing logic
        if (isValidExpense(expense)) {
            expense.setAmount(expense.getAmount() * 1.1); // Example transformation: add 10% extra
            expenseRepository.save(expense);
        } else {
            throw new IllegalArgumentException("Invalid expense details");
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        // Return all expenses directly from the repository
        return expenseRepository.findAll();
    }

    @Override
    public void deleteExpense(String id) {
        // Advanced deletion logic, e.g., logging
        System.out.println("Deleting expense with ID: " + id);
        expenseRepository.deleteById(id);
    }

    @Override
    public void processExpense(Expense expense) {

    }

    private boolean isValidExpense(Expense expense) {
        // Example validation logic
        return expense.getAmount() > 0 && expense.getDate() != null;
    }

    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void unregisterObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(String message) {

    }
}