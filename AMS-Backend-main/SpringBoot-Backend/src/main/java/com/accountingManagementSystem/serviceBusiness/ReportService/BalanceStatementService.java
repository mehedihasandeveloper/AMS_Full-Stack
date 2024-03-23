package com.accountingManagementSystem.serviceBusiness.ReportService;

import com.accountingManagementSystem.serviceBusiness.DTO.BalanceSheetDto;
import com.accountingManagementSystem.serviceBusiness.ReportRepo.BalanceStatementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceStatementService {
    @Autowired
    private BalanceStatementRepo repo;



    public List<BalanceSheetDto> getData(String startDate, String endDate) {
        List<BalanceSheetDto> balanceSheetDtos = new ArrayList<>();

        List<Object[]> objects = repo.getCashDebitCredit(startDate, endDate);
        List<Object[]> objects10 = repo.getReceivableDebitCredit(startDate, endDate);
        List<Object[]> objects20 = repo.getEquipmentDebitCredit(startDate, endDate);
        List<Object[]> objects30 = repo.getSuppliesDebitCredit(startDate, endDate);
        List<Object[]> objects40 = repo.getPayableDebitCredit(startDate, endDate);
        List<Object[]> objects50 = repo.getOE(startDate, endDate);



        for (Object[] objects1 : objects
        ) {
            BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
            balanceSheetDto.setAccountName(String.valueOf(objects1[0]));
            Double number1 = ((Double) objects1[1]);
            Double number2 = ((Double) objects1[2]);
            if (number1 == null) {
                number1 = 0.0;
            }if (number2 == null) {
                number2 = 0.0;
            }
            balanceSheetDto.setBalance(number1-number2);
            balanceSheetDtos.add(balanceSheetDto);
        }
        for (Object[] objects1 : objects10
        ) {
            BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
            balanceSheetDto.setAccountName(String.valueOf(objects1[0]));
            Double number1 = ((Double) objects1[1]);
            Double number2 = ((Double) objects1[2]);
            if (number1 == null) {
                number1 = 0.0;
            }if (number2 == null) {
                number2 = 0.0;
            }
            balanceSheetDto.setBalance(number1-number2);
            balanceSheetDtos.add(balanceSheetDto);
        }
        for (Object[] objects1 : objects20
        ) {
            BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
            balanceSheetDto.setAccountName(String.valueOf(objects1[0]));
            Double number1 = ((Double) objects1[1]);
            Double number2 = ((Double) objects1[2]);
            if (number1 == null) {
                number1 = 0.0;
            }if (number2 == null) {
                number2 = 0.0;
            }
            balanceSheetDto.setBalance(number1-number2);
            balanceSheetDtos.add(balanceSheetDto);
        }
        for (Object[] objects1 : objects30
        ) {
            BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
            balanceSheetDto.setAccountName(String.valueOf(objects1[0]));
            Double number1 = ((Double) objects1[1]);
            Double number2 = ((Double) objects1[2]);
            if (number1 == null) {
                number1 = 0.0;
            }if (number2 == null) {
                number2 = 0.0;
            }
            balanceSheetDto.setBalance(number1-number2);
            balanceSheetDtos.add(balanceSheetDto);
        }
        for (Object[] objects1 : objects40
        ) {
            BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
            balanceSheetDto.setAccountName(String.valueOf(objects1[0]));
            Double number1 = ((Double) objects1[1]);
            Double number2 = ((Double) objects1[2]);
            if (number1 == null) {
                number1 = 0.0;
            }if (number2 == null) {
                number2 = 0.0;
            }
            balanceSheetDto.setBalance(0.0);
            balanceSheetDto.setLiabilityBalance(number2-number1);
            balanceSheetDtos.add(balanceSheetDto);
        }

        for (Object[] objects1 : objects50
        ) {
            BalanceSheetDto balanceSheetDto = new BalanceSheetDto();
            balanceSheetDto.setAccountName("Owner's Equity");
            Double number1 = ((Double) objects1[0]);
            Double number2 = ((Double) objects1[1]);
            Double number3 = ((Double) objects1[2]);
            Double number4 = ((Double) objects1[3]);
            Double number5 = ((Double) objects1[4]);

            if (number1 == null) {
                number1 = 0.0;
            }if (number2 == null) {
                number2 = 0.0;
            }
            if (number3 == null) {
                number3 = 0.0;
            }if (number4 == null) {
                number4 = 0.0;
            }
            if (number5 == null) {
                number5 = 0.0;
            }
            balanceSheetDto.setBalance(0.0);
            balanceSheetDto.setLiabilityBalance(number5);
            balanceSheetDtos.add(balanceSheetDto);
        }


        return balanceSheetDtos;
    }
}
