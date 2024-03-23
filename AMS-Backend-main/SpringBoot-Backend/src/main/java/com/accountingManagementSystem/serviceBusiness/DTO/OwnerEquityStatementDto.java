package com.accountingManagementSystem.serviceBusiness.DTO;

import lombok.Data;

@Data
public class OwnerEquityStatementDto {
    private String entryDate;
    private String transactionId;
    private Double capitalAmount;
    private Double drawingsAmount;
    private Double balance;
}
