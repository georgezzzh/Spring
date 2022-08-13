package com.example.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

//扩展Mvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //视图解析器
    @Bean
    public ViewResolver getViewResolver(){
        return new ViewResolver() {
            @Override
            public View resolveViewName(String viewName, Locale locale) throws Exception {
                return null;
            }
        };
    }
}
