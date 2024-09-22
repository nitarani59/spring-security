package com.example.security.event;

import org.springframework.context.ApplicationEvent;

import com.example.security.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpApplicationEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;

    public UserSignUpApplicationEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
