package com.abing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    //@RestController修饰类下的所有方法，全部都是返回json格式，这样的话不用在方法上加上@ResponseBody
    @RequestMapping("/index")
    public String index(){
        return "success";
    }













}
