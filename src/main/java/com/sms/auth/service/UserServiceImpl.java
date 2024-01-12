package com.sms.auth.service;

import com.sms.auth.entity.User;
import com.sms.auth.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        User newuser=userRepository.save(user);
        return newuser;
    }

    @Override
    public void removeMessage() {
      HttpSession httpSession=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
      httpSession.removeAttribute("message");
    }
}