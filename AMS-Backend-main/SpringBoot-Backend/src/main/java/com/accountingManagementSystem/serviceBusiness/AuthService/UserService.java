package com.accountingManagementSystem.serviceBusiness.AuthService;

import com.accountingManagementSystem.serviceBusiness.AuthModel.Role;
import com.accountingManagementSystem.serviceBusiness.AuthDTO.UserDto;
import com.accountingManagementSystem.serviceBusiness.AuthModel.User;
import com.accountingManagementSystem.serviceBusiness.AuthRepository.RoleRepository;
import com.accountingManagementSystem.serviceBusiness.AuthRepository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
//    public UserService(final UserRepository userRepository01, final RoleRepository roleRepository) {
//        this.userRepository = userRepository01;
//        this.roleRepository = roleRepository;
//    }

    public List<UserDto> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("username"));
//        List<UserDto> userDtos = new ArrayList<>();
//
//        for (User user:users
//             ) {
//            UserDto userDTO = new UserDto();
//           // BeanUtils.copyProperties(u, ud);
//            userDTO.setUsername(user.getUsername());
//            userDTO.setUserFirstName(user.getUserFirstName());
//            userDTO.setUserLastName(user.getUserLastName());
//            userDTO.setPassword(user.getPassword());
//            userDTO.setEmail(user.getEmail());
////            userDTO.setRoles(user.getRoles());
//
//            userDtos.add(userDTO);
//        }
//
//        return userDtos;
//        if (users.size() <= 0){
//            throw new RuntimeException("List is empty!!");
//        }
        return users.stream().map((user) -> mapToDTO(user, new UserDto()))
                .toList();
    }


    public UserDto get(final String userName) {
        Optional<User> user = userRepository.findByUsername(userName);

        return userRepository.findByUsername(userName)
                .map(user01 -> mapToDTO(user01, new UserDto()))
                .orElseThrow(RuntimeException::new);
    }

    public String create(final UserDto userDTO) {

        final User user = new User();
        mapToEntity(userDTO, user);

        user.setPassword(encoder.encode(user.getPassword()));


        return userRepository.save(user).getUsername();
    }

    public void update(final String userName, final UserDto userDTO) {
        final User user01 = userRepository.findByUsername(userName)
                .orElseThrow(RuntimeException::new);
        mapToEntity(userDTO, user01);
        userRepository.save(user01);
    }

    public void delete(final String userName) {
//        User user = new User();
//        user = userRepository.findByUsername(userName).get();
//        System.out.println(user.getId());
//        Long number = user.getId();
//        userRepository.deleteForeignData(number);
        userRepository.deleteByUsername(userName);


    }

    private UserDto mapToDTO(final User user, final UserDto userDTO) {
        userDTO.setUsername(user.getUsername());
        userDTO.setUserFirstName(user.getUserFirstName());
        userDTO.setUserLastName(user.getUserLastName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles() == null ? null : user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet()));
        return userDTO;
    }

    private User mapToEntity(final UserDto userDTO, final User user) {
        user.setUsername(userDTO.getUsername());
        user.setUserFirstName(userDTO.getUserFirstName());
        user.setUserLastName(userDTO.getUserLastName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(true);
        final List<Role> roles = roleRepository.findAllById(userDTO.getRoles() == null ? Collections.emptyList() : userDTO.getRoles());
        if (roles.size() != (userDTO.getRoles() == null ? 0 : userDTO.getRoles().size())) {
            throw new RuntimeException("one of role01s not found");
        }
        user.setRoles(new HashSet<>(roles));
        return user;
    }

    public boolean userNameExists(final String userName) {
        return userRepository.existsByUsernameIgnoreCase(userName);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

}
