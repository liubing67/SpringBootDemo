package com.abing.layui.dao;


import com.abing.layui.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EalenXie on 2018/7/11 14:23
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
