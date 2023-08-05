package com.SpringBoot.MobileService.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/")
public class UserController {
    @GetMapping
    public String getUser(){
        return "Rahul get";
    }
    @PostMapping
    public String createUser(){
        return "Rahul created";
    }
    @PutMapping
    public String uploadUser(){
        return "Rahul uploaded";
    }
    @DeleteMapping
    public String deletUser(){
        return "Rahul deleted";
    }



}
