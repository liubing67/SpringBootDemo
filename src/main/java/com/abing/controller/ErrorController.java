package com.abing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局捕获异常
 */
@RestController
public class ErrorController {

    //全局捕获异常 使用aop技术，采用异常通知
    //如果每个方法都可能会发生异常，每个方法都加上try
    @RequestMapping("/getUser")
    public String getUser(int i){
        int j=1/i;
        return "success"+j;
    }

}
