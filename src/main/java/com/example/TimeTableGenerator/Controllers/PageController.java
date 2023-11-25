package com.example.TimeTableGenerator.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/register")
     String showRegisterPage(){
        return "register";
    }
    @GetMapping("/login")
    String showLoginPage(){
        return "index";
    }
    @GetMapping("/home")
    String showHomePage(){
        return "home";
    }
    @GetMapping("/generate")
    String showGenerationPage(){
        return "generate";
    }
    @GetMapping("/pricing")
    String showPricingPage(){
        return "pricing";
    }


}
