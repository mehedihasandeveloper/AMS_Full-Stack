package com.accountingManagementSystem.serviceBusiness.AuthRepository;


import com.accountingManagementSystem.serviceBusiness.AuthModel.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByRoleNameIgnoreCase(String roleName);

    Role findByRoleDescription(String des);

//    @Query(value = "Select * from users join roles on  where role_description = :des ", nativeQuery = true)
//    Object[] getByRoleDescription(@Param("des") String des);

    @Query(value = " SELECT u.username, r.role_name, u.email " +
            " FROM users u JOIN user_roles un ON un.user_id = u.id " +
            " JOIN roles r ON un.role_id = r.role_name ", nativeQuery = true)
    List<Object[]> findByAsArrayAndSort();

}
