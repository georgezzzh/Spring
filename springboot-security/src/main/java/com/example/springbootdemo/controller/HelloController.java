package com.example.springbootdemo.controller;

import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.pojo.Person;
import com.example.springbootdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("request hello");
        return "hello from IDEA create";
    }

    @RequestMapping("/addPerson")
    public String addPerson(@Validated Person person, BindingResult result)
    {
        String returnInfo = "";
        System.out.println("接收的person:"+person);
        if(result.hasErrors()){
            System.out.println("验证未通过");
            returnInfo = "数据格式有误";
        }else{
            returnInfo = "数据正确";
        }
        return returnInfo;
    }
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/db")
    public String test_datasource(){
        User george = userMapper.findByUserName("george");
        System.out.println(george);
        return george.toString();
    }
    @RequestMapping("/insertDB")
    public String insert_data(){
        User user = new User();
        user.setUsername("root");
        user.setPassword("123456");
        user.setRoles("admin");;
        boolean b = userMapper.insertUser(user);
        if(b)
            return "插入成功";
        else
            return "插入失败";
    }
}
