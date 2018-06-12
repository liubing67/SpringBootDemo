package com.abing;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync //开启异步调用   com.abing.service.MemberService 中的 @Async注解
public class App {

    public static void main(String[] args){
            SpringApplication.run(App.class, args);
    }
}
