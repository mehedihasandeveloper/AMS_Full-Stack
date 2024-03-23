package com.accountingManagementSystem.serviceBusiness.DTO;

import lombok.Data;

@Data
public class AssetsStatementDto {
    private String entryDate;
    private String transactionId;
    private Double cashBalance;
    private Double suppliesBalance;
    private Double equipmentBalance;
    private Double receivableBalance;
    private Double totalAsset;
}
