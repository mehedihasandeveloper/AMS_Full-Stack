package com.accountingManagementSystem.serviceBusiness.Services;

import com.accountingManagementSystem.serviceBusiness.Models.Equipment;
import com.accountingManagementSystem.serviceBusiness.Models.OwnerCapital;
import com.accountingManagementSystem.serviceBusiness.Repositories.OwnerCapitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerCapitalService {
    @Autowired
    public OwnerCapitalRepo repo;

    public List<OwnerCapital> getAllData() {
        return repo.findAll();
    }

    public List<OwnerCapital> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<OwnerCapital> capital = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByTransactionIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<OwnerCapital> transaction = repo.findByTransactionId(id);
            transaction.ifPresent(capital::add);
            return capital;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}
