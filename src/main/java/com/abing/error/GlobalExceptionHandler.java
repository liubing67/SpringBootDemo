package com.abing.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局捕获异常案例
 * 1.捕获返回json格式
 * 2.捕获返回页面
 */
@ControllerAdvice(basePackages = "com.abing.controller")
public class GlobalExceptionHandler {

    //  @ResponseBody b表示返回json格式
    // modeAndView 返回页面
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> errorResult(){
        //实际开发中，将错误记录在日志中。存放在mangdb
        Map<String,Object> errorResultMap=new HashMap<String, Object>();
        errorResultMap.put("errorCode","500");
        errorResultMap.put("errorMsg","系统错误");
        return errorResultMap;
    }
}
