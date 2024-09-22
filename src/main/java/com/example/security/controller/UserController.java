package com.example.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.entity.User;
import com.example.security.event.UserSignUpApplicationEvent;
import com.example.security.model.UserModel;
import com.example.security.repository.UserRepository;
import com.example.security.service.UserService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private UserService userService;
    // @Autowired
    // private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public String signUp(@RequestBody UserModel userModel, HttpServletRequest request) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setName(userModel.getName());
        user.setPassword(userModel.getPassword());
        userRepository.save(user);
        publisher.publishEvent(new UserSignUpApplicationEvent(user, "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath()));

        return "Registered successfully.";
    }

    @GetMapping("/login")
    public List<User> getMethodName() {
        return userRepository.findAll();
    }

    @GetMapping("/verifyToken")
    public String verifyToken(@RequestParam("token") String token) {
        return userService.verifyToken(token);
    }

    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest httpServletRequest) {
        String newToken = userService.resendVerificationToken(oldToken);
        if (newToken == "Invalid token") return "BadRequest";
        return "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + "/verifyToken?token=" + newToken;
    }
}
