package com.accountingManagementSystem.serviceBusiness.Services;

import com.accountingManagementSystem.serviceBusiness.Models.OwnerCapital;
import com.accountingManagementSystem.serviceBusiness.Models.OwnerDrawings;
import com.accountingManagementSystem.serviceBusiness.Repositories.OwnerDrawingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerDrawingsService {
    @Autowired
    public OwnerDrawingsRepo repo;

    public List<OwnerDrawings> getAllData() {
        return repo.findAll();
    }

    public List<OwnerDrawings> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<OwnerDrawings> drawings = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByTransactionIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<OwnerDrawings> transaction = repo.findByTransactionId(id);
            transaction.ifPresent(drawings::add);
            return drawings;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}
