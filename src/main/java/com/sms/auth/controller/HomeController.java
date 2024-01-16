package com.sms.auth.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.auth.entity.User;
import com.sms.auth.entity.apiData;
import com.sms.auth.repository.UserRepository;
import com.sms.auth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
//@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    apiData apiData=new apiData();

    @Autowired
    private ObjectMapper objectMapper;

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

        String uri = "https://f30f-182-191-146-99.ngrok-free.app/mail";
//        String email=user.getEmail();
//        System.out.println(email);
        apiData apiData = new apiData();
        apiData.setTo("zaeemmavia321@gmail.com");
        apiData.setSubject("Login Notification");
        apiData.setBody("You have been logged in successfully");
        apiData.setMethod("GOOGLE_SMTP");


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(uri, apiData, apiData.class);
        return "login";
    }

//    @GetMapping("/login")
//    public String login(User user) {
//        return "login";
//    }


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
        System.out.println(user.toString());
        User user1=userService.saveUser(user);

        if (user1!=null){
//            System.out.println("save successfully");
            httpSession.setAttribute("message","Register Successfully");
        }else {
//            System.out.println("No Added");
            httpSession.setAttribute("message","Something Went Wrong, Please try again");
        }
        System.out.println(user.getEmail());

        String uri = "https://f30f-182-191-146-99.ngrok-free.app/mail";

        String email=user.getEmail();
        apiData apiData = new apiData();
        apiData.setTo(email);
        apiData.setSubject("Register Notification");
        apiData.setBody("You are registered successfully");
        apiData.setMethod("GOOGLE_SMTP");


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(uri, apiData, apiData.class);

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

    @GetMapping("/test")
    public String getTest(){
        String uri="https://f30f-182-191-146-99.ngrok-free.app/test";
        RestTemplate restTemplate=new RestTemplate();
        String test=restTemplate.getForObject(uri,String.class);
        return test;
    }

//    @PostMapping("/mail")
//    public ResponseEntity<apiData> getEmail(@RequestBody JsonNode requestBody) {
//        String uri = "https://f30f-182-191-146-99.ngrok-free.app/mail";
//
//        String to = requestBody.get("to").asText();
//        String subject = requestBody.get("subject").asText();
//        String body = requestBody.get("body").asText();
//        String method = requestBody.get("method").asText();
//
//        apiData apiData = new apiData(to, subject, body, method);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<apiData> responseEntity = restTemplate.postForEntity(uri, apiData, apiData.class);
//
//        return responseEntity;
//    }

//    @PostMapping("/mail")
//    public ResponseEntity<apiData> getEmail(@RequestBody JsonNode requestBody) {
//        String uri = "https://f30f-182-191-146-99.ngrok-free.app/mail";
//
//        String to = requestBody.get("to").asText();
//        String subject = requestBody.get("subject").asText();
//        String method = requestBody.get("method").asText();
//
//        User user = objectMapper.convertValue(requestBody.get("user"), User.class);
//
//        String body = "Dear " + user.getName() + ", you are registered successfully.";
//
//        apiData apiData = new apiData(to, subject, body, method, user.getName());
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<apiData> responseEntity = restTemplate.postForEntity(uri, apiData, apiData.class);
//
//        return responseEntity;
//    }

}
