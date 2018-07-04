package com.abing.ratelimiter.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public boolean addOrder(){
        System.out.println("db......操作数据库！");
        return true;
    }
}
