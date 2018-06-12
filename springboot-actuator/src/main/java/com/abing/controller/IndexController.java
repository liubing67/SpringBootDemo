package com.abing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        String res="SpringBoot 2.0  -----V3.0";
        return res;
    }
}
