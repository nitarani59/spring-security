package com.example.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.entity.User;
import com.example.security.model.UserModel;
import com.example.security.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/signup")
    public String signUp(@RequestBody UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setName(userModel.getName());
        user.setPassword((userModel.getPassword()));
        userRepository.save(user);
        return "Registered successfully.";
    }

    @GetMapping("/login")
    public List<User> getMethodName() {
        return userRepository.findAll();
    }
}
