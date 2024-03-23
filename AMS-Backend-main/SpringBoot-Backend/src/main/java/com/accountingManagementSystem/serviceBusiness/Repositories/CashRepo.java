package com.accountingManagementSystem.serviceBusiness.Repositories;

import com.accountingManagementSystem.serviceBusiness.Models.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CashRepo extends JpaRepository<Cash, Long>{
    @Query(value = "SELECT * FROM cash ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Cash getLastData();

    @Query(value = "SELECT * FROM cash WHERE transaction_id = ?", nativeQuery = true)
    Cash getUpdateData(Long id);
    @Query(value = "SELECT balance AS total FROM cash ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCashBalance();

    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-01-01' AND '2024-01-31'", nativeQuery = true)
    String getCashJan();
    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-02-01' AND '2024-02-29'", nativeQuery = true)
    String getCashFeb();
    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-03-01' AND '2024-03-31'", nativeQuery = true)
    String getCashMar();

    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-04-01' AND '2024-04-30'", nativeQuery = true)
    String getCashApr();

    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-05-01' AND '2024-05-31'", nativeQuery = true)
    String getCashMay();

    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-06-01' AND '2024-06-30'", nativeQuery = true)
    String getCashJun();

    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-07-01' AND '2024-07-31'", nativeQuery = true)
    String getCashJul();
    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-08-01' AND '2024-08-31'", nativeQuery = true)
    String getCashAug();
    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-09-01' AND '2024-09-30'", nativeQuery = true)
    String getCashSep();

    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-10-01' AND '2024-10-31'", nativeQuery = true)
    String getCashOct();

    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-11-01' AND '2024-11-30'", nativeQuery = true)
    String getCashNov();

    @Query(value = "SELECT SUM(credit_amount) FROM cash WHERE entry_date BETWEEN '2024-12-01' AND '2024-12-31'", nativeQuery = true)
    String getCashDec();



    List<Cash> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);

    List<Cash> findByTransactionIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    Optional<Cash> findByTransactionId(Long id);
}






