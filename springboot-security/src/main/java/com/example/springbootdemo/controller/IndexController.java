package com.example.springbootdemo.controller;

import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class IndexController {
    @RequestMapping({"/", "/index", "index.html"})
    public String index(Model model){
        model.addAttribute("msg","<h1>thymeleaf test</h1>");
        model.addAttribute("users", Arrays.asList("tom", "ford", "tick"));
        return "index";
    }
    @RequestMapping({"/login.html", })
    public String toLogin(){
        System.out.println("权限拦截，跳转到登录页面处理");
        return "views/login";
    }
    @RequestMapping("/successLogin")
    public String successLogin(){
        System.out.println("成功登录");
        return "views/success";
    }
    @RequestMapping("/fail")
    public String failLogin(){
        System.out.println("登录失败");
        return "views/fail";
    }
    @RequestMapping("/user/{page}")
    public String userPermit(@PathVariable("page") int id){
        return "views/user/"+id;
    }
    @RequestMapping("/admin/{page}")
    public String adminPermit(@PathVariable("page") int id){
        return "views/admin/"+id;
    }
    @RequestMapping("/root/{page}")
    public String rootPermit(@PathVariable("page") int id){
        return "views/root/"+id;
    }
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/register")
    public @ResponseBody String register(User user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //设置成默认的user角色
        if(user.getRoles().equals(""))
            user.setRoles("user");
        boolean b = userMapper.insertUser(user);
        if(b) return "注册成功";
        else return "注册失败";
    }
}
