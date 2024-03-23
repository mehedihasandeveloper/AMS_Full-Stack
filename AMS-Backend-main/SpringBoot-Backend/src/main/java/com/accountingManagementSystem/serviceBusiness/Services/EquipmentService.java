package com.accountingManagementSystem.serviceBusiness.Services;

import com.accountingManagementSystem.serviceBusiness.Models.Cash;
import com.accountingManagementSystem.serviceBusiness.Models.Equipment;
import com.accountingManagementSystem.serviceBusiness.Repositories.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    @Autowired
    public EquipmentRepo repo;

    public List<Equipment> getAllData() {
        return repo.findAll();
    }

    public Double getEquipmentBalance() {
        return repo.getEquipmentBalance();
    }

    public List<Equipment> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<Equipment> equipment = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByTransactionIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<Equipment> transaction = repo.findByTransactionId(id);
            transaction.ifPresent(equipment::add);
            return equipment;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}
