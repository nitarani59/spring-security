package com.example.security.service.impl;

import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.entity.TokenVerification;
import com.example.security.entity.User;
import com.example.security.repository.TokenVerificationRepository;
import com.example.security.repository.UserRepository;
import com.example.security.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private TokenVerificationRepository tokenVerificationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveToken(User user, String token) {
        Long expirationTime = 600l;
        TokenVerification tokenVerification = new TokenVerification();
        tokenVerification.setToken(token);
        tokenVerification.setUser(user);
        tokenVerification.setExpirationTimeInSeconds(expirationTime);
        tokenVerificationRepository.save(tokenVerification);
    }

    @Override
    public String verifyToken(String token) {
       TokenVerification tokenVerification = tokenVerificationRepository.findByToken(token);
       if (tokenVerification == null) return "Bad Request";
        User user = tokenVerification.getUser();
        userRepository.save(user);
       return "Valid";
    }

    @Override
    public String resendVerificationToken(String oldToken) {
        TokenVerification tokenVerification = tokenVerificationRepository.findByToken(oldToken);
        if (tokenVerification == null) {
            return "Invalid token";
        }
        User user = tokenVerification.getUser();
        String newToken = UUID.randomUUID().toString();
        tokenVerification.setToken(newToken);
        tokenVerificationRepository.save(tokenVerification);
        return newToken;
    }
}
