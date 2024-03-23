package com.accountingManagementSystem.serviceBusiness.Services;

import com.accountingManagementSystem.serviceBusiness.DTO.IncomeStatementDto;
import com.accountingManagementSystem.serviceBusiness.Models.Revenue;
import com.accountingManagementSystem.serviceBusiness.Repositories.RevenueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class RevenueService {
    @Autowired
    public RevenueRepo repo;

    public List<Revenue> getAllData() {
        return repo.findAll();
    }

    public List<IncomeStatementDto> getData(String startDate, String endDate) {
        List<IncomeStatementDto> incomeStatementDtos = new ArrayList<>();

        List<Object[]> objects = repo.getData(startDate, endDate);

        for (Object[] objects1 : objects
        ) {
            System.out.println(objects1[0]);
            IncomeStatementDto incomeStatementDto = new IncomeStatementDto();
            incomeStatementDto.setEntryDate(String.valueOf(objects1[0]));
            incomeStatementDto.setTransactionId(String.valueOf(objects1[1]));
            incomeStatementDto.setRevenueBalance((Double) objects1[2]);
            incomeStatementDto.setExpenseBalance((Double) objects1[3]);
            Double number1 = ((Double) objects1[2]);
            Double number2 = ((Double) objects1[3]);
            if (number1  == null) {
                number1 = 0.0;
            } if (number2  == null){
                number2 = 0.0;
            }
            System.out.println(number1);
            System.out.println(number2);
            incomeStatementDto.setNetIncome(number1-number2);
            incomeStatementDtos.add(incomeStatementDto);
        }


        return incomeStatementDtos;
    }

    public Double getRevenueBalance() {
        return repo.getRevenueBalance();
    }

    public List<Double> getRevenueList() {
        List<Double> revenueList = new ArrayList<>();
        revenueList.add(repo.getRevenueJan());
        revenueList.add(repo.getRevenueFeb());
        revenueList.add(repo.getRevenueMar());
        revenueList.add(repo.getRevenueApr());
        revenueList.add(repo.getRevenueMay());
        revenueList.add(repo.getRevenueJun());
        revenueList.add(repo.getRevenueJul());
        revenueList.add(repo.getRevenueAug());
        revenueList.add(repo.getRevenueSep());
        revenueList.add(repo.getRevenueOct());
        revenueList.add(repo.getRevenueNov());
        revenueList.add(repo.getRevenueDec());
        System.out.println(revenueList);
        return revenueList;
    }

    public List<Revenue> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<Revenue> revenue = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByTransactionIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<Revenue> transaction = repo.findByTransactionId(id);
            transaction.ifPresent(revenue::add);
            return revenue;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}
