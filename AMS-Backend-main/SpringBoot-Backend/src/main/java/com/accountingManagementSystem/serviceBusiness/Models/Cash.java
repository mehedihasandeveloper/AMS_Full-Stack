package com.accountingManagementSystem.serviceBusiness.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Cash extends EntityBase {


    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

}
