package com.sms.auth.controller;

import com.sms.auth.entity.User;
import com.sms.auth.repository.UserRepository;
import com.sms.auth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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

//    @GetMapping("/user/profile")
//    public String profile(Principal principal, Model model){
////        String email = principal.getName();
////        User user = userRepository.findByEmail(email);
////        model.addAttribute("user",user);
//        return "profile";
//    }
//
//    @GetMapping("/user/home")
//    public String home(){
//        return "home";
//    }

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

    @ModelAttribute
    public void commonUser(Principal principal,Model model){
        if (principal !=null){
            String email = principal.getName();
            User user = userRepository.findByEmail(email);
            model.addAttribute("user",user);
        }
    }
}
