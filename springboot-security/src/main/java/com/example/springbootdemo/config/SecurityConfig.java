package com.example.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig{
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //请求授权规则
        http.authorizeHttpRequests().antMatchers("/login.html").permitAll()
                .antMatchers("/user/**").hasRole("USER") //对应数据库的权限字段是ROLE_USER
                .antMatchers("/admin/**").hasRole("ADMIN") //ROLE_ADMIN
                .antMatchers("/root/**").hasRole("ROOT"); //ROLE_ROOT
        //定制自己的登录页面
        http.formLogin().loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                //.successForwardUrl("/successLogin") //登录成功之后跳转的请求
                .failureForwardUrl("/fail") //登录失败跳转的请求
                .and().csrf().disable(); //关闭csrf，否则无法请求
        //开启记住我 功能
        http.rememberMe();
        //开启注销功能, 注销成功回到首页
        http.logout();
        return http.build();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
        /*
        内存用户管理器
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
        PasswordEncoder passwordEncoder = passwordEncoder();
        users.createUser(User.withUsername("george").password(passwordEncoder.encode("123456")).roles("vip1", "vip2", "vip3").build());
        users.createUser(User.withUsername("tom").password(passwordEncoder.encode("123456")).roles("vip2").build());
        users.createUser(User.withUsername("ming").password(passwordEncoder.encode("123456")).roles("vip3").build());
        return users;
         */
    }
}
