package com.accountingManagementSystem.serviceBusiness.AuthModel;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size( min = 3, max = 120)
    private String password;
    private String userFirstName;
    private String userLastName;
    private boolean credentialsNonExpired;
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(Long id, String username, String email, String password, String userFirstName, String userLastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }
    public User( String username, String email, String password, String userFirstName, String userLastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;

    }

    public User(Long id, String username, String email, String password, String userFirstName, String userLastName, boolean credentialsNonExpired, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.roles = roles;
    }
}
