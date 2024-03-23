package com.accountingManagementSystem.serviceBusiness.Repositories;

import com.accountingManagementSystem.serviceBusiness.Models.AccountReceivable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsReceivableRepo extends JpaRepository<AccountReceivable, Long> {

    @Query(value = "SELECT * FROM account_receivable ORDER BY id DESC LIMIT 1", nativeQuery = true)
    AccountReceivable getLastData();
    @Query(value = "SELECT * FROM account_receivable WHERE transaction_id = ?", nativeQuery = true)
    AccountReceivable getUpdateData(Long id);


    @Query(value = "SELECT balance AS total FROM account_receivable ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getDuePaymentBalance();

    List<AccountReceivable> findByTransactionIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    Optional<AccountReceivable> findByTransactionId(Long id);

    List<AccountReceivable> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
