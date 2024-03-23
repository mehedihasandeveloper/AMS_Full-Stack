package com.accountingManagementSystem.serviceBusiness.Controllers;

import com.accountingManagementSystem.serviceBusiness.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class DashboardController {
    @Autowired
    public CashService cashService;

    @Autowired
    public RevenueService revenueService;

    @Autowired
    public SuppliesService suppliesService;

    @Autowired
    public EquipmentService equipmentService;

    @Autowired
    public AccountReceivableService receivableService;
    @Autowired
    public AccountsPayableService payableService;

    @GetMapping("/cash")
    public Double getCashBalance() {
        return cashService.getCashBalance();
    }

    @GetMapping("/receivable")
    public Double getReceivableBalance() {
        return receivableService.getReceivableBalance();
    }

    @GetMapping("/Supplies")
    public Double getSuppliesBalance() {
        return suppliesService.getSuppliesBalance();
    }

    @GetMapping("/Equipment")
    public Double getEquipmentBalance() {
        return equipmentService.getEquipmentBalance();
    }

    @GetMapping("/revenue")
    public Double getRevenueBalance() {
        return revenueService.getRevenueBalance();
    }

    @GetMapping("/doughnut")
    public List<Double> getDoughnut() {
        List<Double> doughnut = new ArrayList<>();
        doughnut.add(getCashBalance());
        doughnut.add(getEquipmentBalance());
        doughnut.add(getSuppliesBalance());
        doughnut.add(getReceivableBalance());
        return doughnut;
    }

    @GetMapping("/loan")
    public Double getLoanBalance() {
        return payableService.getPayableBalance();
    }

    @GetMapping("/getCashSpending")
    public List<String> getAllCashSpending() {
        return cashService.getCashSpending();
    }

    @GetMapping("/getRevenue")
    public List<Double> getHalfYearRevenue() {
        return revenueService.getRevenueList();
    }



}
