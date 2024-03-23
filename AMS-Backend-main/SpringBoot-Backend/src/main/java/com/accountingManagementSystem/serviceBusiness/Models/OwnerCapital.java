package com.accountingManagementSystem.serviceBusiness.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OwnerCapital extends EntityBase{


    @ManyToOne(targetEntity=Transaction.class)
    @JoinColumn(name="transaction_id")
    private Transaction transaction;


}
