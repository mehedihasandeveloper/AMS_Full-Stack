package com.accountingManagementSystem.serviceBusiness.Models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder

@Table(name = "FILE_DATA")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;
    private String name;
    private String type;
    private String filePath;

    @ManyToOne(targetEntity=Transaction.class)
    @JoinColumn(name="transaction_id")
    private Transaction transaction;


    public FileData() {
    }

    public FileData(Long id, String name, String type, String filePath, Transaction transaction) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.filePath = filePath;
        this.transaction = transaction;
    }

    public FileData(String name, String type, String filePath, Transaction transaction) {
        this.name = name;
        this.type = type;
        this.filePath = filePath;
        this.transaction = transaction;
    }
}
