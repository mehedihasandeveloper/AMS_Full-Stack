package com.accountingManagementSystem.serviceBusiness.Models;




import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AccountsPayable extends EntityBase{


    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
