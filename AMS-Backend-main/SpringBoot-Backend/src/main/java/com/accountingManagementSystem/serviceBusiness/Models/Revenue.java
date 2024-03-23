package com.accountingManagementSystem.serviceBusiness.Models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Revenue extends EntityBase{

    @ManyToOne(cascade = CascadeType.DETACH)
    private Transaction transaction;

}
