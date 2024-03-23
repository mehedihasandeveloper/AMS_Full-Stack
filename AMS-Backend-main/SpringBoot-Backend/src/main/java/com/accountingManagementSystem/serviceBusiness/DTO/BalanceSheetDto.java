package com.accountingManagementSystem.serviceBusiness.DTO;

import lombok.Data;

@Data
public class BalanceSheetDto {
    private String accountName;
    private Double balance;
    private Double liabilityBalance;
}
