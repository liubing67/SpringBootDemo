package com.abing.test02.service;

import com.abing.test02.mapper.UserMapperTest02;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceTest02 {
    @Autowired
    private UserMapperTest02 userMapperTest02;

    @Transactional(transactionManager = "test2TransactionManager")
    public int insertUser(String name,Integer age){
        int insertUserResult=userMapperTest02.insert(name,age);
        log.info("insertUserResult:{}",insertUserResult);
        int i=1/age;
        return insertUserResult;
    }
}
