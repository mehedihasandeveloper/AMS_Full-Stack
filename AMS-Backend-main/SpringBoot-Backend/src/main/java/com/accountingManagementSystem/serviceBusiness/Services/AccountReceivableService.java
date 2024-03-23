package com.accountingManagementSystem.serviceBusiness.Services;

import com.accountingManagementSystem.serviceBusiness.Models.AccountReceivable;
import com.accountingManagementSystem.serviceBusiness.Models.Cash;
import com.accountingManagementSystem.serviceBusiness.Models.OwnerDrawings;
import com.accountingManagementSystem.serviceBusiness.Repositories.AccountsReceivableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountReceivableService {
    @Autowired
    public AccountsReceivableRepo repo;

    public List<AccountReceivable> getAllData() {
        return repo.findAll();
    }

    public Double getReceivableBalance() {
        return repo.getDuePaymentBalance();
    }

    public List<AccountReceivable> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<AccountReceivable> receivable = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByTransactionIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<AccountReceivable> transaction = repo.findByTransactionId(id);
            transaction.ifPresent(receivable::add);
            return receivable;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}
