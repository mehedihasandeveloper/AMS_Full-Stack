package com.accountingManagementSystem.serviceBusiness.ReportRepo;


import com.accountingManagementSystem.serviceBusiness.Models.TrialBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrialBalanceRepo extends JpaRepository<TrialBalance, Long> {
    @Query(value = "SELECT 'Cash' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM cash WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getCashDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Receivable' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM account_receivable WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getReceivableDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Payable' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM accounts_payable WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getPayableDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Equipment' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM equipment WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getEquipmentDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Expense' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM expense WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getExpenseDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Owner Capital' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM owner_capital WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getCapitalDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Owner Drawings' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM owner_drawings WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getDrawingsDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Revenue' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM revenue WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getRevenueDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Supplies' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM supplies WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getSuppliesDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);
}
