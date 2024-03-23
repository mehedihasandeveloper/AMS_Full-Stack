package com.accountingManagementSystem.serviceBusiness.Repositories;

import com.accountingManagementSystem.serviceBusiness.Models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    @Query(value = "SELECT * FROM expense ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Expense getLastData();
    @Query(value = "SELECT * FROM expense WHERE transaction_id = ?", nativeQuery = true)
    Expense getUpdateData(Long id);

    List<Expense> findByTransactionIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    Optional<Expense> findByTransactionId(Long id);

    List<Expense> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
