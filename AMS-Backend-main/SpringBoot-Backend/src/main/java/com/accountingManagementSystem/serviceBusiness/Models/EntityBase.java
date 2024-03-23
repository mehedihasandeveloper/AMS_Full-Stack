package com.accountingManagementSystem.serviceBusiness.Models;


import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;


@MappedSuperclass
@Data
//@Entity
public abstract class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate entryDate;
    private Double debitAmount;
    private Double creditAmount;
    private String description;
    private Double balance;
}
