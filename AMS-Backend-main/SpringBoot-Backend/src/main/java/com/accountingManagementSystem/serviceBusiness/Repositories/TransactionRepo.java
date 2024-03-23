package com.accountingManagementSystem.serviceBusiness.Repositories;

import com.accountingManagementSystem.serviceBusiness.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {


    List<Transaction> findByIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
