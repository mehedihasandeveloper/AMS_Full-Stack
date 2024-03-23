package com.accountingManagementSystem.serviceBusiness.AuthController;



import com.accountingManagementSystem.serviceBusiness.AuthDTO.MessageResponse;
import com.accountingManagementSystem.serviceBusiness.AuthDTO.UserDto;
import com.accountingManagementSystem.serviceBusiness.AuthService.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "userName") final String userName) {
        return ResponseEntity.ok(userService.get(userName));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody final UserDto userDTO)
            throws MethodArgumentNotValidException {
        if (userService.userNameExists(userDTO.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.emailExists(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        userDTO.setRoles(Collections.singleton("Admin"));
        try {
            userService.create(userDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("one of role not found"));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<Void> updateUser(@PathVariable(name = "userName") final String userName,
            @RequestBody @Valid final UserDto userDTO) {
        userService.update(userName, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userName}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userName") final String userName) {
        userService.delete(userName);
        return ResponseEntity.noContent().build();
    }

}
