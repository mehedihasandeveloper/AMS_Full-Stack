package com.accountingManagementSystem.serviceBusiness.ReportRepo;



import com.accountingManagementSystem.serviceBusiness.Models.BalanceStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceStatementRepo extends JpaRepository<BalanceStatement, Long> {
    @Query(value = "SELECT 'Cash' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM cash WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List<Object[]> getCashDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Receivable' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM account_receivable WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getReceivableDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Equipment' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM equipment WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getEquipmentDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Supplies' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM supplies WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getSuppliesDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT 'Payable' AS account_name, SUM(debit_amount) AS debit_balance, SUM(credit_amount) AS  credit_balance  FROM accounts_payable WHERE entry_date BETWEEN :startDate AND  :endDate ", nativeQuery = true)
    List <Object[]> getPayableDebitCredit(@Param("startDate") String std, @Param("endDate") String endDate);


    @Query(value = "SELECT \n" +
            "    (SELECT COALESCE(SUM(debit_amount), 0) FROM expense WHERE entry_date BETWEEN :startDate AND :endDate) AS total_expense,\n" +
            "    (SELECT COALESCE(SUM(debit_amount), 0) FROM owner_drawings WHERE entry_date BETWEEN :startDate AND :endDate) AS total_drawings,\n" +
            "    (SELECT COALESCE(SUM(credit_amount), 0) FROM revenue WHERE entry_date BETWEEN :startDate AND :endDate) AS total_revenue,\n" +
            "    (SELECT COALESCE(SUM(credit_amount), 0) FROM owner_capital WHERE entry_date BETWEEN :startDate AND :endDate) AS total_capital,\n" +
            "    (COALESCE((SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN :startDate AND :endDate), 0) +\n" +
            "     COALESCE((SELECT SUM(credit_amount) FROM owner_capital WHERE entry_date BETWEEN :startDate AND :endDate), 0))-\n" +
            "    (COALESCE((SELECT SUM(debit_amount) FROM expense WHERE entry_date BETWEEN :startDate AND :endDate), 0) +\n" +
            "    COALESCE((SELECT SUM(debit_amount) FROM owner_drawings WHERE entry_date BETWEEN :startDate AND :endDate), 0)) AS balance;", nativeQuery = true)
    List <Object[]> getOE(@Param("startDate") String std, @Param("endDate") String endDate);

}
