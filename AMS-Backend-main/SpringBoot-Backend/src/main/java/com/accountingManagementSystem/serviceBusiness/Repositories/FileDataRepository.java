package com.accountingManagementSystem.serviceBusiness.Repositories;

import com.accountingManagementSystem.serviceBusiness.Models.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

    @Query(value = "SELECT * FROM FILE_DATA where name = :name limit 1", nativeQuery = true)
    public FileData findAllSortedByNameUsingNative(@Param(value = "name") String name );

    @Query(value = "SELECT * FROM file_data WHERE transaction_id = ?", nativeQuery = true)
    FileData getUpdateData(Long id);
}
