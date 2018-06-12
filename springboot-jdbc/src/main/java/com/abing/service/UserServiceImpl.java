package com.abing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void createUser(String name, Integer age) {
        jdbcTemplate.update("INSERT INTO USERS VALUES (NULL ,?,?);",name,age);
    }
}
