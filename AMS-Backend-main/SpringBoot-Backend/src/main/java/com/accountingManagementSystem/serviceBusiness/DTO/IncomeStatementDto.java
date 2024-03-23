package com.accountingManagementSystem.serviceBusiness.DTO;

import lombok.Data;


@Data
public class IncomeStatementDto {
    private String entryDate;
    private String transactionId;
    private Double revenueBalance;
    private Double expenseBalance;
    private Double netIncome;

}
