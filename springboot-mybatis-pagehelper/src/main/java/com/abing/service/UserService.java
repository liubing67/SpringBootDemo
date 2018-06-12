package com.abing.service;

import com.abing.entity.User;
import com.abing.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public PageInfo<User> findUserList(int page, int size) {
        // 开启分页插件,放在查询语句上面   mysql 查询 limit oraclet 伪列  sqlserver top
        PageHelper.startPage(page, size);
        List<User> userList = userMapper.findUserList();
        // 封装分页之后的数据
        PageInfo<User> userPageInfo=new PageInfo<User>(userList);
        return userPageInfo;
    }
}
