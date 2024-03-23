package com.accountingManagementSystem.serviceBusiness.Services;

import com.accountingManagementSystem.serviceBusiness.Models.AccountsPayable;
import com.accountingManagementSystem.serviceBusiness.Repositories.AccountsPayableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsPayableService {
    @Autowired
    public AccountsPayableRepo repo;

    public List<AccountsPayable> getAllData() {
        return repo.findAll();
    }

    public Double getPayableBalance() {
        return  repo.getLoanBalance();
    }

    public List<AccountsPayable> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<AccountsPayable> payable = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByTransactionIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<AccountsPayable> transaction = repo.findByTransactionId(id);
            transaction.ifPresent(payable::add);
            return payable;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}
