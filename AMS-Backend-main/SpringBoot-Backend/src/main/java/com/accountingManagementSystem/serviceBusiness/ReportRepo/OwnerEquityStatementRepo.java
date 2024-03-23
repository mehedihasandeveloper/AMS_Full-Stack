package com.accountingManagementSystem.serviceBusiness.ReportRepo;


import com.accountingManagementSystem.serviceBusiness.Models.OwnerEquityStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerEquityStatementRepo extends JpaRepository<OwnerEquityStatement, Long> {


    @Query(value = "SELECT entry_date, transaction_id, credit_amount AS capital_amount, NULL AS drawings_amount FROM owner_capital  WHERE entry_date BETWEEN :startDate AND  :endDate \n" +
            "    UNION ALL\n" +
            "    SELECT entry_date, transaction_id,NULL AS capital_amount, debit_amount AS drawings_amount  FROM owner_drawings  WHERE entry_date BETWEEN :startDate AND  :endDate", nativeQuery = true)
    List<Object[]> getData(@Param("startDate") String std, @Param("endDate") String endDate);

}
