package com.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    
    @GetMapping("/loginpage")
    public String logIn(){
        return "loginpage";
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }
}
