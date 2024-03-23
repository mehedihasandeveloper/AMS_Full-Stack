package com.accountingManagementSystem.serviceBusiness.Services;


import com.accountingManagementSystem.serviceBusiness.Models.Expense;
import com.accountingManagementSystem.serviceBusiness.Repositories.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    public ExpenseRepo repo;

    public List<Expense> getAllData() {
        return repo.findAll();
    }

    public List<Expense> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<Expense> expense = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByTransactionIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<Expense> transaction = repo.findByTransactionId(id);
            transaction.ifPresent(expense::add);
            return expense;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}
