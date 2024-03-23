package com.accountingManagementSystem.serviceBusiness.ReportService;

import com.accountingManagementSystem.serviceBusiness.DTO.OwnerEquityStatementDto;
import com.accountingManagementSystem.serviceBusiness.ReportRepo.OwnerEquityStatementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerEquityStatementService {
    @Autowired
    private OwnerEquityStatementRepo repo;

    public List<OwnerEquityStatementDto> getData(String startDate, String endDate) {
        List<OwnerEquityStatementDto> ownerEquityStatementDtos = new ArrayList<>();

        List<Object[]> objects = repo.getData(startDate, endDate);

        for (Object[] objects1 : objects
        ) {
            System.out.println(objects1[0]);
            OwnerEquityStatementDto ownerEquityStatementDto = new OwnerEquityStatementDto();
            ownerEquityStatementDto.setEntryDate(String.valueOf(objects1[0]));
            ownerEquityStatementDto.setTransactionId(String.valueOf(objects1[1]));
            ownerEquityStatementDto.setCapitalAmount((Double) objects1[2]);
            ownerEquityStatementDto.setDrawingsAmount((Double) objects1[3]);
            Double number1 = ((Double) objects1[2]);
            Double number2 = ((Double) objects1[3]);
            if (number1  == null) {
                number1 = 0.0;
            } if (number2  == null){
                number2 = 0.0;
            }
            System.out.println(number1);
            System.out.println(number2);
            ownerEquityStatementDto.setBalance(number1-number2);
            ownerEquityStatementDtos.add(ownerEquityStatementDto);
        }


        return ownerEquityStatementDtos;
    }
}
