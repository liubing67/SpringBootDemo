package com.abing.dao;

import com.abing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<User,Integer>   统一返回User   Integer表示主键
public interface UserDao  extends JpaRepository<User,Integer>{
}
