package com.accountingManagementSystem.serviceBusiness.Repositories;

import com.accountingManagementSystem.serviceBusiness.Models.OwnerDrawings;
import com.accountingManagementSystem.serviceBusiness.Models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerDrawingsRepo extends JpaRepository<OwnerDrawings, Long> {
    @Query(value = "SELECT * FROM owner_drawings ORDER BY id DESC LIMIT 1", nativeQuery = true)
    OwnerDrawings getLastData();
    @Query(value = "SELECT * FROM owner_drawings WHERE transaction_id = ?", nativeQuery = true)
    OwnerDrawings getUpdateData(Long id);

    List<OwnerDrawings> findByTransactionIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    Optional<OwnerDrawings> findByTransactionId(Long id);

    List<OwnerDrawings> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
