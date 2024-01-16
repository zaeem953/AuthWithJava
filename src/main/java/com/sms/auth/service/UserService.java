package com.sms.auth.service;

import com.sms.auth.entity.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);

    public void removeMessage();
    List<User> getAllStudent();
}
