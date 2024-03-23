package com.accountingManagementSystem.serviceBusiness.Repositories;


import com.accountingManagementSystem.serviceBusiness.Models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Long> {

    @Query(value = "SELECT * FROM equipment ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Equipment getLastData();
    @Query(value = "SELECT * FROM equipment WHERE transaction_id = ?", nativeQuery = true)
    Equipment getUpdateData(Long id);

    @Query(value = "SELECT balance AS total FROM equipment ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getEquipmentBalance();

    List<Equipment> findByTransactionIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    Optional<Equipment> findByTransactionId(Long id);

    List<Equipment> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
