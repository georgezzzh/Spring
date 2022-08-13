package com.example.springbootdemo.mapper;

import com.example.springbootdemo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //@Insert("inser into user(username,password,roles) values(#{username}, #{password}, #{roles})")
    boolean insertUser(User user);
    //@Insert("select * from user where username=#{name}")
    User findByUserName(String name);
}
