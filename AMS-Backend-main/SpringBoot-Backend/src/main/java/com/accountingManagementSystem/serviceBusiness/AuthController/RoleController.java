package com.accountingManagementSystem.serviceBusiness.AuthController;



import com.accountingManagementSystem.serviceBusiness.AuthDTO.RoleDto;
import com.accountingManagementSystem.serviceBusiness.AuthService.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(roleService.findAllUser());
    }
    @GetMapping("/{roleName}")
    public ResponseEntity<RoleDto> getRole(@PathVariable(name = "roleName") final String roleName) {
        return ResponseEntity.ok(roleService.get(roleName));
    }

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody final RoleDto RoleDto) {
        roleService.create(RoleDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{roleName}")
    public ResponseEntity<Void> updateRole(@PathVariable(name = "roleName") final String roleName,
            @RequestBody @Valid final RoleDto RoleDto) {
        roleService.update(roleName, RoleDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{roleName}")
    public ResponseEntity<Void> deleteRole(@PathVariable(name = "roleName") final String roleName) {
        roleService.delete(roleName);
        return ResponseEntity.noContent().build();
    }



}
