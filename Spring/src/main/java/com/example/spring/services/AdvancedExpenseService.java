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
            // Apply a business rule: add 10% extra to the amount as a processing fee or tax
            expense.setAmount(expense.getAmount() * 1.1);
            expenseRepository.save(expense);
            notifyObservers("Advanced processing: Expense added with title: " + expense.getTitle());
        } else {
            throw new IllegalArgumentException("Invalid expense details");
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        // Potentially add filtering or sorting logic if needed
        return expenseRepository.findAll();
    }

    @Override
    public void deleteExpense(String id) {

        System.out.println("Advanced processing: Deleting expense with ID: " + id);
        expenseRepository.deleteById(id);
        notifyObservers("Advanced processing: Expense deleted with ID: " + id);
    }

    @Override
    public void processExpense(Expense expense) {
        addExpense(expense);
    }

    private boolean isValidExpense(Expense expense) {

        return expense.getAmount() > 0 && expense.getDate() != null;
    }

    @Override
    public void registerObserver(Observer observer) {
        // Register observers if needed
    }

    @Override
    public void unregisterObserver(Observer observer) {
        // Unregister observers if needed
    }

    @Override
    public void notifyObservers(String message) {
        // Notify observers if there are any registered
    }
}