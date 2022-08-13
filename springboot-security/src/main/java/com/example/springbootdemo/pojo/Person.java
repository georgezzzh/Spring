package com.example.springbootdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
//@ConfigurationProperties(prefix = "person")
//数据校验
@Validated
public class Person {
    @NotNull
    private String name;
    @Min(1)
    @Max(120)
    private int age;
    @Email
    private String email;
    private boolean happy;
    private LocalDate birthday;
    private Map<String, Object> maps;
    private List<Object> list;
    private Dog dog;
}
