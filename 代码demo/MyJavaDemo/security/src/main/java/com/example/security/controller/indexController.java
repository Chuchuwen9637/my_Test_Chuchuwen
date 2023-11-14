package com.example.security.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {


    @RequestMapping("/index")
    public String indexController(){
        return "idnex";
    }
}
