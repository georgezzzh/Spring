package com.sonydafa.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/example")
public class ExampleController {


    @RequestMapping(value="/test",produces = "text/json;charset=UTF-8")
    public @ResponseBody
    String getTest()
    {
        System.out.println("test接收到");
        return "msg:test success";
    }

}
