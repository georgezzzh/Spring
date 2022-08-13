package com.example.springbootdemo.config;

import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(username);
        if(user==null)
            throw new UsernameNotFoundException("用户不存在");
        System.out.println("查询出来的user"+user);
        String roles = user.getRoles();
        String[] singleRole = roles.split(",");
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(String s: singleRole) grantedAuthorities.add(new SimpleGrantedAuthority(s));
        //返回一个Spring Security 内部的User对象
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), grantedAuthorities);
    }
}
