package com.accountingManagementSystem.serviceBusiness.Models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;


@Entity
@Setter
@Getter
public class Transaction extends CommonBase {
    private String userName;
    private LocalDate entryDate;
    private String debitAccount;
    private String creditAccount;
    private Double amount;
    private String description;
}
