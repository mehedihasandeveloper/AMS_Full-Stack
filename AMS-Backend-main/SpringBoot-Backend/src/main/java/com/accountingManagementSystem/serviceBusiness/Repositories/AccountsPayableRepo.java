package com.accountingManagementSystem.serviceBusiness.Repositories;


import com.accountingManagementSystem.serviceBusiness.Models.AccountsPayable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsPayableRepo extends JpaRepository<AccountsPayable, Long> {
    @Query(value = "SELECT * FROM accounts_payable ORDER BY id DESC LIMIT 1", nativeQuery = true)
    AccountsPayable getLastData();
    @Query(value = "SELECT * FROM accounts_payable WHERE transaction_id = ?", nativeQuery = true)
    AccountsPayable getUpdateData(Long id);

    @Query(value = "SELECT balance AS total FROM accounts_payable ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getLoanBalance();

    List<AccountsPayable> findByTransactionIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    Optional<AccountsPayable> findByTransactionId(Long id);

    List<AccountsPayable> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
