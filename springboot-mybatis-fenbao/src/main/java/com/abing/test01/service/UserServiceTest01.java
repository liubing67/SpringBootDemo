package com.abing.test01.service;

import com.abing.test01.mapper.UserMapperTest01;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceTest01 {
    @Autowired
    private UserMapperTest01 userMapperTest01;

    @Transactional(transactionManager = "test1TransactionManager")
    public int insertUser(String name,Integer age){
        int insertUserResult=userMapperTest01.insert(name,age);
        log.info("insertUserResult:{}",insertUserResult);
        int i=1/age;
        return insertUserResult;
    }
}
