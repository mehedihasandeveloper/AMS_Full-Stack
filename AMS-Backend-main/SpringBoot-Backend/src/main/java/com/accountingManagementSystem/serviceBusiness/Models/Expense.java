package com.accountingManagementSystem.serviceBusiness.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Expense extends EntityBase{
    @ManyToOne(targetEntity=Transaction.class)
    @JoinColumn(name="transaction_id")
    private Transaction transaction;
}
