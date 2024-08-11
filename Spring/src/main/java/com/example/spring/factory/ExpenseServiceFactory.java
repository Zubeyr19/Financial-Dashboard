package com.example.spring.factory;

import com.example.spring.services.AdvancedExpenseService;
import com.example.spring.services.BasicExpenseService;
import com.example.spring.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpenseServiceFactory {

    private final BasicExpenseService basicExpenseService;
    private final AdvancedExpenseService advancedExpenseService;

    @Autowired
    public ExpenseServiceFactory(BasicExpenseService basicExpenseService,
                                 AdvancedExpenseService advancedExpenseService) {
        this.basicExpenseService = basicExpenseService;
        this.advancedExpenseService = advancedExpenseService;
    }

    public ExpenseService createExpenseService(String type) {
        if ("basic".equalsIgnoreCase(type)) {
            return basicExpenseService;
        } else if ("advanced".equalsIgnoreCase(type)) {
            return advancedExpenseService;
        } else {
            throw new IllegalArgumentException("Unknown service type");
        }
    }
}