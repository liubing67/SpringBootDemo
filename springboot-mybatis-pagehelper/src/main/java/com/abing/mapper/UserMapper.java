package com.abing.mapper;

import com.abing.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM USERS")
    List<User> findUserList();
}
