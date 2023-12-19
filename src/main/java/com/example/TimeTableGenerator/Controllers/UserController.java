
package com.example.TimeTableGenerator.Controllers;

import com.example.TimeTableGenerator.DTOs.LoginDTO;
import com.example.TimeTableGenerator.DTOs.UserDTO;
import com.example.TimeTableGenerator.Entities.Role;
import com.example.TimeTableGenerator.Entities.UserEntity;
import com.example.TimeTableGenerator.Repositories.RoleRepository;
import com.example.TimeTableGenerator.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDTO user, Model model) {
        UserEntity userEntity1 = userRepository.findUserByUsername(user.getUsername());
        String userAlreadyExists = "User Already Exists";
        String success = "Registration Successful";
        if(userEntity1 != null) {
            model.addAttribute("registerMessage", userAlreadyExists);
            return new ResponseEntity<>(userAlreadyExists, HttpStatus.BAD_REQUEST);
        }
        UserEntity registeredUserEntity = new UserEntity();
        registeredUserEntity.setUsername(user.getUsername());
        registeredUserEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        registeredUserEntity.setName(user.getName());
        Role role = roleRepository.findByName("USER");
        registeredUserEntity.setRole(Collections.singletonList(role));
        registeredUserEntity.setEnabled(Boolean.TRUE);
        model.addAttribute("registerMessage", success);
        userRepository.save(registeredUserEntity);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Login Successful", HttpStatus.OK);
    }

}