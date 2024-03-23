package com.accountingManagementSystem.serviceBusiness.AuthModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
@Getter
@Setter
@RequiredArgsConstructor
public class Role {

    @Id
    @Column(nullable = false, updatable = false)
    private String roleName;

    @Column
    private String roleDescription;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    private Boolean active = false;

    @PrePersist
    void createdAt() {
        this.dateCreated = this.lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    void updatedAt() {
        this.lastUpdated = LocalDateTime.now();
    }
}
