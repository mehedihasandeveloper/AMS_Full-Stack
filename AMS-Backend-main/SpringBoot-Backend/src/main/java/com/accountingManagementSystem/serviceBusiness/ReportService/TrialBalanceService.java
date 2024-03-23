package com.accountingManagementSystem.serviceBusiness.ReportService;

import com.accountingManagementSystem.serviceBusiness.DTO.TrialBalanceDto;
import com.accountingManagementSystem.serviceBusiness.ReportRepo.TrialBalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrialBalanceService {
    @Autowired
    private TrialBalanceRepo repo;

    public List<TrialBalanceDto> getData(String startDate, String endDate) {
        List<TrialBalanceDto> trialBalanceDtos = new ArrayList<>();

        List<Object[]> objects = repo.getCashDebitCredit(startDate, endDate);
        List<Object[]> objects10 = repo.getReceivableDebitCredit(startDate, endDate);
        List<Object[]> objects20 = repo.getPayableDebitCredit(startDate, endDate);
        List<Object[]> objects30 = repo.getEquipmentDebitCredit(startDate, endDate);
        List<Object[]> objects40 = repo.getExpenseDebitCredit(startDate, endDate);
        List<Object[]> objects50 = repo.getCapitalDebitCredit(startDate, endDate);
        List<Object[]> objects60 = repo.getDrawingsDebitCredit(startDate, endDate);
        List<Object[]> objects70 = repo.getRevenueDebitCredit(startDate, endDate);
        List<Object[]> objects80 = repo.getSuppliesDebitCredit(startDate, endDate);


        for (Object[] objects1 : objects
        ) {
            TrialBalanceDto trialBalanceDto = new TrialBalanceDto();
            trialBalanceDto.setAccountName(String.valueOf(objects1[0]));
            trialBalanceDto.setDebitBalance((Double) objects1[1]);
            trialBalanceDto.setCreditBalance((Double) objects1[2]);

            trialBalanceDtos.add(trialBalanceDto);
        }
        for (Object[] objects1 : objects10
        ) {
            TrialBalanceDto trialBalanceDto = new TrialBalanceDto();
            trialBalanceDto.setAccountName(String.valueOf(objects1[0]));
            trialBalanceDto.setDebitBalance((Double) objects1[1]);
            trialBalanceDto.setCreditBalance((Double) objects1[2]);

            trialBalanceDtos.add(trialBalanceDto);
        }
        for (Object[] objects1 : objects20
        ) {
            TrialBalanceDto trialBalanceDto = new TrialBalanceDto();
            trialBalanceDto.setAccountName(String.valueOf(objects1[0]));
            trialBalanceDto.setDebitBalance((Double) objects1[1]);
            trialBalanceDto.setCreditBalance((Double) objects1[2]);

            trialBalanceDtos.add(trialBalanceDto);
        }
        for (Object[] objects1 : objects30
        ) {
            TrialBalanceDto trialBalanceDto = new TrialBalanceDto();
            trialBalanceDto.setAccountName(String.valueOf(objects1[0]));
            trialBalanceDto.setDebitBalance((Double) objects1[1]);
            trialBalanceDto.setCreditBalance((Double) objects1[2]);

            trialBalanceDtos.add(trialBalanceDto);
        }
        for (Object[] objects1 : objects40
        ) {
            TrialBalanceDto trialBalanceDto = new TrialBalanceDto();
            trialBalanceDto.setAccountName(String.valueOf(objects1[0]));
            trialBalanceDto.setDebitBalance((Double) objects1[1]);
            trialBalanceDto.setCreditBalance((Double) objects1[2]);

            trialBalanceDtos.add(trialBalanceDto);
        }
        for (Object[] objects1 : objects50
        ) {
            TrialBalanceDto trialBalanceDto = new TrialBalanceDto();
            trialBalanceDto.setAccountName(String.valueOf(objects1[0]));
            trialBalanceDto.setDebitBalance((Double) objects1[1]);
            trialBalanceDto.setCreditBalance((Double) objects1[2]);

            trialBalanceDtos.add(trialBalanceDto);
        }
        for (Object[] objects1 : objects60
        ) {
            TrialBalanceDto trialBalanceDto = new TrialBalanceDto();
            trialBalanceDto.setAccountName(String.valueOf(objects1[0]));
            trialBalanceDto.setDebitBalance((Double) objects1[1]);
            trialBalanceDto.setCreditBalance((Double) objects1[2]);

            trialBalanceDtos.add(trialBalanceDto);
        }
        for (Object[] objects1 : objects70
        ) {
            TrialBalanceDto trialBalanceDto = new TrialBalanceDto();
            trialBalanceDto.setAccountName(String.valueOf(objects1[0]));
            trialBalanceDto.setDebitBalance((Double) objects1[1]);
            trialBalanceDto.setCreditBalance((Double) objects1[2]);

            trialBalanceDtos.add(trialBalanceDto);
        }
        for (Object[] objects1 : objects80
        ) {
            TrialBalanceDto trialBalanceDto = new TrialBalanceDto();
            trialBalanceDto.setAccountName(String.valueOf(objects1[0]));
            trialBalanceDto.setDebitBalance((Double) objects1[1]);
            trialBalanceDto.setCreditBalance((Double) objects1[2]);

            trialBalanceDtos.add(trialBalanceDto);
        }


        return trialBalanceDtos;
    }
}
