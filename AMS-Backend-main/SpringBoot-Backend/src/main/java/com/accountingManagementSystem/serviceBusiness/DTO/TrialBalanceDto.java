package com.accountingManagementSystem.serviceBusiness.DTO;

import lombok.Data;

@Data
public class TrialBalanceDto {
    private String accountName;
    private Double debitBalance;
    private Double creditBalance;
}
