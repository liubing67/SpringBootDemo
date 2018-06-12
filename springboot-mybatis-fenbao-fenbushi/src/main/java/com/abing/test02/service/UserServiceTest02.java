package com.abing.test02.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abing.test01.mapper.UserMapperTest01;
import com.abing.test02.mapper.UserMapperTest02;

@Service
@Slf4j
public class UserServiceTest02 {
    @Autowired
    private UserMapperTest02 userMapperTest02;
    @Autowired
    private UserMapperTest01 userMapperTest01;

    @Transactional()
    public int insertUser(String name,Integer age){
        int insertUserResult=userMapperTest02.insert(name,age);
        log.info("insertUserResult:{}",insertUserResult);
        int i=1/age;
        return insertUserResult;
    }

    @Transactional()
    public int insertUserTest01AndTest02(String name,Integer age){
        //第一个数据源
        int insertUserResult01=userMapperTest01.insert(name,age);
        //第二个数据源
        int insertUserResult02=userMapperTest02.insert(name,age);
        int i=1/age;
        int result=insertUserResult01+insertUserResult02;
        //test01能成功    test02不能成功  又因为用的是@Transactional(transactionManager = "test2TransactionManager")
        return result;
    }
}
