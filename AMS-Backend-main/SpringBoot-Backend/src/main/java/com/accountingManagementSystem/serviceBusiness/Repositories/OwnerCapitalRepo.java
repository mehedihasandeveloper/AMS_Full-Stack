package com.accountingManagementSystem.serviceBusiness.Repositories;

import com.accountingManagementSystem.serviceBusiness.Models.Cash;
import com.accountingManagementSystem.serviceBusiness.Models.OwnerCapital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerCapitalRepo extends JpaRepository<OwnerCapital, Long> {
    @Query(value = "SELECT * FROM owner_capital ORDER BY id DESC LIMIT 1", nativeQuery = true)
    OwnerCapital getLastData();
    @Query(value = "SELECT * FROM owner_capital WHERE transaction_id = ?", nativeQuery = true)
    OwnerCapital getUpdateData(Long id);

    List<OwnerCapital> findByTransactionIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    Optional<OwnerCapital> findByTransactionId(Long id);

    List<OwnerCapital> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
