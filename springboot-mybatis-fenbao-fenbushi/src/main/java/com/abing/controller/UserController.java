package com.abing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abing.test01.service.UserServiceTest01;
import com.abing.test02.service.UserServiceTest02;

@RestController
public class UserController {

    @Autowired
    private UserServiceTest01 userServiceTest01;
    @Autowired
    private UserServiceTest02 userServiceTest02;
    @RequestMapping("/userServiceTest01")
    public Integer userServiceTest01(String name,Integer age){
        return userServiceTest01.insertUser(name,age);
    }

    @RequestMapping("/userServiceTest02")
    public Integer userServiceTest02(String name,Integer age){
        return userServiceTest02.insertUser(name,age);
    }
    @RequestMapping("/insertUserTest01AndTest02")
    public int insertUserTest01AndTest02(String name,Integer age){
        return userServiceTest02.insertUserTest01AndTest02(name,age);
    }



}
