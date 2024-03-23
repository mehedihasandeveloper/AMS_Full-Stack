package com.accountingManagementSystem.serviceBusiness.AuthService;


import com.accountingManagementSystem.serviceBusiness.AuthDTO.RoleDto;
import com.accountingManagementSystem.serviceBusiness.AuthDTO.UserRoleDto;
import com.accountingManagementSystem.serviceBusiness.AuthModel.Role;
import com.accountingManagementSystem.serviceBusiness.AuthRepository.RoleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> findAll() {
        final List<Role> Roles = roleRepository.findAll(Sort.by("roleName"));

        List<Object[]> ur = roleRepository.findByAsArrayAndSort();

        for (Object[] ob : ur
        ) {
            System.out.println(ob[0]);
        }

        return Roles.stream()
                .map((Role) -> mapToDTO(Role, new RoleDto()))
                .toList();
    }



    public List<UserRoleDto> findAllUser() {
        List<Object[]> ur = roleRepository.findByAsArrayAndSort();
        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        for (Object[] ob : ur
        ) {
            UserRoleDto dto = new UserRoleDto();
            dto.setUsername(ob[0].toString());
            dto.setRoleName(ob[1].toString());
            dto.setTest1(ob[2].toString());
            userRoleDtos.add(dto);
        }
        return userRoleDtos;
    }






    public RoleDto get(final String roleName) {
        return roleRepository.findById(roleName)
                .map(Role -> mapToDTO(Role, new RoleDto()))
                .orElseThrow(RuntimeException::new);
    }

    public String create(final RoleDto RoleDto) {
        final Role Role = new Role();
        mapToEntity(RoleDto, Role);
        Role.setRoleName(RoleDto.getRoleName());
        return roleRepository.save(Role).getRoleName();
    }

    public void update(final String roleName, final RoleDto RoleDto) {
        final Role Role = roleRepository.findById(roleName)
                .orElseThrow(RuntimeException::new);
        mapToEntity(RoleDto, Role);
        roleRepository.save(Role);
    }

    public void delete(final String roleName) {
        roleRepository.deleteById(roleName);
    }

    private RoleDto mapToDTO(final Role Role, final RoleDto RoleDto) {
        RoleDto.setRoleName(Role.getRoleName());
        RoleDto.setRoleDescription(Role.getRoleDescription());
        RoleDto.setActive(Role.getActive());
        return RoleDto;
    }

    private Role mapToEntity(final RoleDto RoleDto, final Role Role) {
        Role.setRoleDescription(RoleDto.getRoleDescription());
        Role.setActive(RoleDto.getActive());
        return Role;
    }

    public boolean roleNameExists(final String roleName) {
        return roleRepository.existsByRoleNameIgnoreCase(roleName);
    }



}
