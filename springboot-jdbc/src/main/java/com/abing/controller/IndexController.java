package com.abing.controller;

import com.abing.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/index")
    public String index(String name,Integer age){
        userServiceImpl.createUser(name,age);
        return "success";
    }
}
