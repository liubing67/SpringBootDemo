package com.abing.controller;

import com.abing.entity.User;
import com.abing.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findUserList")
    public PageInfo<User> findUserList(int page,int rows){
        PageInfo<User> pageInfo=userService.findUserList(page,rows);
        System.out.println(pageInfo.getPageNum());
        return pageInfo;
    }
}
