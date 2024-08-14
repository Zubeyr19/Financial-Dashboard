package com.example.spring.services;

import com.example.spring.model.Expense;
import com.example.spring.observer.Observer;
import com.example.spring.observer.Subject;
import com.example.spring.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicExpenseService implements ExpenseService, Subject {

    private final List<Observer> observers = new ArrayList<>();
    private final ExpenseRepository expenseRepository;

    @Autowired
    public BasicExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void addExpense(Expense expense) {

        if (isValidExpense(expense)) {
            expenseRepository.save(expense);
            // Notify observers after adding the expense
            notifyObservers("Basic processing: Expense added with title: " + expense.getTitle());
        } else {
            throw new IllegalArgumentException("Invalid expense details");
        }
    }

    @Override
    public List<Expense> getAllExpenses() {

        return expenseRepository.findAll();
    }

    @Override
    public void deleteExpense(String id) {
        // Basic deletion logic: Directly delete the expense by ID
        expenseRepository.deleteById(id);
        // Notify observers after deleting the expense
        notifyObservers("Basic processing: Expense deleted with ID: " + id);
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
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}