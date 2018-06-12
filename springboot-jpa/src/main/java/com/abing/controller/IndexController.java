package com.abing.controller;

import com.abing.dao.UserDao;
import com.abing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class IndexController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/insertUserJpa")
    public User insertUserJpa(User user){
        User user1=userDao.save(user);
        return user1;
    }

    @RequestMapping("/findUser")
    public Object findUser(User user){
        System.out.println("----------"+user.getId());
        Optional<User> userOptional=userDao.findById(user.getId());
        User user1=userOptional.get();
        return user1==null?"没有数据":user1;
    }
}
