package com.accountingManagementSystem.serviceBusiness.Services;

import com.accountingManagementSystem.serviceBusiness.Models.Cash;
import com.accountingManagementSystem.serviceBusiness.Models.Transaction;
import com.accountingManagementSystem.serviceBusiness.Repositories.CashRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CashService {
    @Autowired
    public CashRepo repo;

    public List<Cash> getAllData() {
        return repo.findAll();
    }

    public Double getCashBalance() {
        return repo.getCashBalance();
    }

    public List<String> getCashSpending(){
        List<String> cashSpendingList = new ArrayList<>();
        cashSpendingList.add(repo.getCashJan());
        cashSpendingList.add(repo.getCashFeb());
        cashSpendingList.add(repo.getCashMar());
        cashSpendingList.add(repo.getCashApr());
        cashSpendingList.add(repo.getCashMay());
        cashSpendingList.add(repo.getCashJun());
        cashSpendingList.add(repo.getCashJul());
        cashSpendingList.add(repo.getCashAug());
        cashSpendingList.add(repo.getCashSep());
        cashSpendingList.add(repo.getCashOct());
        cashSpendingList.add(repo.getCashNov());
        cashSpendingList.add(repo.getCashDec());
        System.out.println(cashSpendingList);
        return cashSpendingList;
    }

    public List<Cash> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<Cash> cash = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByTransactionIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<Cash> transaction = repo.findByTransactionId(id);
            transaction.ifPresent(cash::add);
            return cash;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}
