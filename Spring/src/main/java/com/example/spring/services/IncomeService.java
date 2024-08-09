package com.example.spring.services;

import com.example.spring.model.Income;

import java.util.List;

public interface IncomeService {
        void addIncome(Income income);
        List<Income> getAllIncomes();
        void deleteIncome(String id);

    void processIncome(Income income);
}
