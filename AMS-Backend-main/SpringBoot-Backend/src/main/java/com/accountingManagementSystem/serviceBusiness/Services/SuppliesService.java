package com.accountingManagementSystem.serviceBusiness.Services;

import com.accountingManagementSystem.serviceBusiness.DTO.AssetsStatementDto;
import com.accountingManagementSystem.serviceBusiness.Models.Supplies;
import com.accountingManagementSystem.serviceBusiness.Repositories.SuppliesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuppliesService {
    @Autowired
    public SuppliesRepo repo;

    public List<Supplies> getAllData() {
        return repo.findAll();
    }

    public List<AssetsStatementDto> getData(String startDate, String endDate) {
        List<AssetsStatementDto> assetsStatementDtos = new ArrayList<>();

        List<Object[]> objects = repo.getData(startDate, endDate);

        for (Object[] objects1 : objects
        ) {
            System.out.println(objects1[0]);
            AssetsStatementDto assetsStatementDto = new AssetsStatementDto();
            assetsStatementDto.setEntryDate(String.valueOf(objects1[0]));
            assetsStatementDto.setTransactionId(String.valueOf(objects1[1]));
            assetsStatementDto.setCashBalance((Double) objects1[2]);
            assetsStatementDto.setEquipmentBalance((Double) objects1[3]);
            assetsStatementDto.setSuppliesBalance((Double) objects1[4]);
            assetsStatementDto.setReceivableBalance((Double) objects1[5]);
            Double number1 = ((Double) objects1[2]);
            Double number2 = ((Double) objects1[3]);
            Double number3 = ((Double) objects1[4]);
            Double number4 = ((Double) objects1[5]);
            if (number1 == null) {
                number1 = 0.0;
            }
            if(number2 == null) {
                number2 = 0.0;
            }
            if(number3 == null) {
                number3 = 0.0;
            }
            if(number4 == null) {
                number4 = 0.0;
            }
            System.out.println(number1);
            System.out.println(number2);
            System.out.println(number3);
            System.out.println(number4);

            assetsStatementDto.setTotalAsset(number1 + number2 + number3 + number4);
            assetsStatementDtos.add(assetsStatementDto);
        }
        return assetsStatementDtos;
    }

    public Double getSuppliesBalance() {
        return repo.getSuppliesBalance();
    }

    public List<Supplies> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<Supplies> supplies = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByTransactionIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<Supplies> transaction = repo.findByTransactionId(id);
            transaction.ifPresent(supplies::add);
            return supplies;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}
