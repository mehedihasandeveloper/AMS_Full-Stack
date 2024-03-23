package com.accountingManagementSystem.serviceBusiness.AuthRepository;


import com.accountingManagementSystem.serviceBusiness.AuthModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String userName);

    boolean existsByUsernameIgnoreCase(String userName);

    boolean existsByEmailIgnoreCase(String email);

    void deleteByUsername(String userName);

    @Query(value = "DELETE FROM user_roles WHERE user_id = ?", nativeQuery = true)
    void deleteForeignData(Long id);
}
