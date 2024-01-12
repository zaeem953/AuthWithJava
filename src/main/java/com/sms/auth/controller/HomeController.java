package com.sms.auth.controller;

import com.sms.auth.entity.User;
import com.sms.auth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession httpSession){
//        System.out.println(user);
        User user1=userService.saveUser(user);

        if (user1!=null){
//            System.out.println("save successfully");
            httpSession.setAttribute("message","Register Successfully");
        }else {
//            System.out.println("No Added");
            httpSession.setAttribute("message","Something Went Wrong, Please try again");
        }

        return "redirect:/register";
    }
}
