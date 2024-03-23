package com.accountingManagementSystem.serviceBusiness.Repositories;

import com.accountingManagementSystem.serviceBusiness.Models.Equipment;
import com.accountingManagementSystem.serviceBusiness.Models.Supplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SuppliesRepo extends JpaRepository<Supplies, Long> {
    @Query(value = "SELECT * FROM supplies ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Supplies getLastData();
    @Query(value = "SELECT * FROM supplies WHERE transaction_id = ?", nativeQuery = true)
    Supplies getUpdateData(Long id);

    @Query(value = "SELECT entry_date, transaction_id, balance AS cash_balance, NULL AS equipment_balance, NULL AS supplies_balance, NULL AS receivable_balance FROM cash WHERE entry_date BETWEEN :startDate AND :endDate \n" +
            "UNION ALL\n" +
            "            SELECT entry_date, transaction_id, NULL AS cash_balance, balance AS equipment_balance, NULL AS supplies_balance,  NULL AS receivable_balance  FROM equipment  WHERE entry_date BETWEEN :startDate AND :endDate \n" +
            "             UNION ALL \n" +
            "             SELECT entry_date, transaction_id, NULL AS cash_balance, NULL AS equipment_balance, balance AS supplies_balance,    NULL AS receivable_balance  FROM supplies WHERE entry_date BETWEEN :startDate AND :endDate \n" +
            "            UNION ALL\n" +
            "            SELECT entry_date, transaction_id, NULL AS cash_balance, NULL AS equipment_balance, NULL AS supplies_balance, balance AS receivable_balance FROM account_receivable WHERE entry_date BETWEEN :startDate AND :endDate ", nativeQuery = true)
    List<Object[]> getData(@Param("startDate") String std, @Param("endDate") String endDate);

    @Query(value = "SELECT balance AS total FROM supplies ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getSuppliesBalance();


    List<Supplies> findByTransactionIdAndEntryDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    Optional<Supplies> findByTransactionId(Long id);

    List<Supplies> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
