package com.sms.auth.service;

import com.sms.auth.entity.User;
import com.sms.auth.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setRole("ROLE_USER");
        User newuser=userRepository.save(user);
        return newuser;
    }

    @Override
    public void removeMessage() {
      HttpSession httpSession=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
      httpSession.removeAttribute("message");
    }

    @Override
    public List<User> getAllStudent() {
        return (List<User>) userRepository.findAll();
    }
}
