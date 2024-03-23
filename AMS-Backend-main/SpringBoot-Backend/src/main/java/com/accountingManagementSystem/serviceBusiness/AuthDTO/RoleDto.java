package com.accountingManagementSystem.serviceBusiness.AuthDTO;

import lombok.Data;

@Data
public class RoleDto {
    private String roleName;
    private String roleDescription;
    private Boolean active;

}
