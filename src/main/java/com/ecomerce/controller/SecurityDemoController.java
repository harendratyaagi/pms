package com.ecomerce.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityDemoController {


    @GetMapping("welcome")
    public String welcome(){
        return "welcome product management";
    }

    @GetMapping("/text")
    public String greeting(){
        return "greeting product management";
    }

    @GetMapping("/home")
    public String nonSecure(){
        return "home product management";
    }
}
