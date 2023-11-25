
package com.example.TimeTableGenerator.Controllers;

import com.example.TimeTableGenerator.Entities.User;
import com.example.TimeTableGenerator.Repositories.UserRepository;
import com.example.TimeTableGenerator.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user, Model model) {
        List<User> users= userRepository.findUsersByEmail(user.getEmail());
        String userAlreadyExists = "User Already Exists";
        String success = "Registration Successful";
        if(!users.isEmpty()) {
            model.addAttribute("registerMessage", userAlreadyExists);
            return new ResponseEntity<>(userAlreadyExists, HttpStatus.UNAUTHORIZED);
        }
        userService.createUser(user);
        model.addAttribute("registerMessage", success);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }



    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, Model model) {
        String email= user.getEmail();
        String password = user.getPassword();

        if(!emailExists(email)){
            return new ResponseEntity<>("Username does not exist", HttpStatus.UNAUTHORIZED);
        }

        String storedPassword = userService.getHashedPasswordByEmail(email);

        boolean passwordExists = userService.verifyPassword(password, storedPassword);

        if(!passwordExists){
            return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Login Successful", HttpStatus.OK);
    }

    public boolean emailExists(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            return false;
        }
        return true;
    }
}