package com.example.demo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;


//只用@Mapper注解标注，然后继承BaseMapper即可，就可以使用一系列查询功能
@Mapper
public interface UserDao extends BaseMapper<User>{

}
