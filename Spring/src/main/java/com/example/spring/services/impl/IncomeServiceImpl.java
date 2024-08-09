package com.example.spring.services.impl;

import com.example.spring.model.Income;
import com.example.spring.repository.IncomeRepository;
import com.example.spring.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    public void addIncome(Income income) {
        incomeRepository.save(income);
    }

    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public void deleteIncome(String id) {
        incomeRepository.deleteById(id);
    }

    @Override
    public void processIncome(Income income) {
        addIncome(income);  // Additional processing logic if necessary
    }
}