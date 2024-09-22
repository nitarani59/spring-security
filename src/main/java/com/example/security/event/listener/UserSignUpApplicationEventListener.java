package com.example.security.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.security.entity.User;
import com.example.security.event.UserSignUpApplicationEvent;
import com.example.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserSignUpApplicationEventListener implements ApplicationListener<UserSignUpApplicationEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(UserSignUpApplicationEvent event) {
        // send this application url to user via mail.
        String token = UUID.randomUUID().toString();
        String applicationUrl = event.getApplicationUrl() + "/verifyToken?token=" + token;
        User user = event.getUser();
        userService.saveToken(user, token);
        log.info(applicationUrl);
    }
    
}
