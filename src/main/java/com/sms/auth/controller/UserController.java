package com.sms.auth.controller;

import com.sms.auth.entity.User;
import com.sms.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @ModelAttribute
    public void commonUser(Principal principal, Model model){
        if (principal !=null){
            String email = principal.getName();
            User user = userRepository.findByEmail(email);
            model.addAttribute("user",user);
        }
    }
}
