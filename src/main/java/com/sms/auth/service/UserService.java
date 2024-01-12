package com.sms.auth.service;

import com.sms.auth.entity.User;

public interface UserService {
    public User saveUser(User user);

    public void removeMessage();
}
