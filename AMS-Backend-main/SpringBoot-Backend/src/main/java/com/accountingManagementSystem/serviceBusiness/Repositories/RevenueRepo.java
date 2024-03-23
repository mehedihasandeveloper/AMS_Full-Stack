package com.accountingManagementSystem.serviceBusiness.Repositories;


import com.accountingManagementSystem.serviceBusiness.Models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RevenueRepo extends JpaRepository<Revenue, Long> {
    @Query(value = "SELECT * FROM revenue ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Revenue getLastData();
    @Query(value = "SELECT * FROM revenue WHERE transaction_id = ?", nativeQuery = true)
    Revenue getUpdateData(Long id);

    @Query(value = "SELECT entry_date, transaction_id, credit_amount AS revenue_amount, NULL AS expense_balance  FROM revenue  WHERE entry_date BETWEEN :startDate AND  :endDate \n" +
            "    UNION ALL\n" +
            "    SELECT entry_date, transaction_id, NULL AS revenue_amount, debit_amount AS expense_amount  FROM expense  WHERE entry_date BETWEEN :startDate AND  :endDate", nativeQuery = true)
    List<Object[]> getData(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT balance AS total FROM revenue ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getRevenueBalance();

    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-01-01' AND '2024-01-31'", nativeQuery = true)
    Double getRevenueJan();
    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-02-01' AND '2024-02-29'", nativeQuery = true)
    Double getRevenueFeb();
    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-03-01' AND '2024-03-31'", nativeQuery = true)
    Double getRevenueMar();

    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-04-01' AND '2024-04-30'", nativeQuery = true)
    Double getRevenueApr();

    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-05-01' AND '2024-05-31'", nativeQuery = true)
    Double getRevenueMay();

    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-06-01' AND '2024-06-30'", nativeQuery = true)
    Double getRevenueJun();

    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-07-01' AND '2024-07-31'", nativeQuery = true)
    Double getRevenueJul();
    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-08-01' AND '2024-08-31'", nativeQuery = true)
    Double getRevenueAug();
    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-09-01' AND '2024-09-30'", nativeQuery = true)
    Double getRevenueSep();

    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-10-01' AND '2024-10-31'", nativeQuery = true)
    Double getRevenueOct();

    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-11-01' AND '2024-11-30'", nativeQuery = true)
    Double getRevenueNov();

    @Query(value = "SELECT SUM(credit_amount) FROM revenue WHERE entry_date BETWEEN '2024-12-01' AND '2024-12-31'", nativeQuery = true)
    Double getRevenueDec();

    List<Revenue> findByTransactionIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    Optional<Revenue> findByTransactionId(Long id);

    List<Revenue> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
