package com.example.spring.services.impl;

import com.example.spring.events.ExpenseAddedEvent;
import com.example.spring.model.Expense;
import com.example.spring.observer.Observer;
import com.example.spring.repository.ExpenseRepository;
import com.example.spring.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    private ApplicationEventPublisher eventPublisher;

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
        notifyObservers("Expense added: " + expense.getTitle());
        eventPublisher.publishEvent(new ExpenseAddedEvent(expense.getTitle()));
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
        notifyObservers("Expense deleted: " + id);
    }

    @Override
    public void processExpense(Expense expense) {
        // Implementation of processExpense
        addExpense(expense);
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
