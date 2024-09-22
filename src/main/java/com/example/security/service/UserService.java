package com.example.security.service;

import com.example.security.entity.User;

public interface UserService {
    public void saveToken(User user, String token);
    public String verifyToken(String token);
    public String resendVerificationToken(String oldToken);
}
