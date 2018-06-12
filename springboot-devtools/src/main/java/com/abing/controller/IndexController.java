package com.abing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/indexDev")
    public String indexDev(){
        String res="SpringBoot 2.0  -----V3.0";
        return res;
    }
    @RequestMapping("/myIndexDev")
    public String myIndexDev(){
        String res="myIndexDev    SpringBoot 2.0  -----V3.0";
        return res;
    }
}
