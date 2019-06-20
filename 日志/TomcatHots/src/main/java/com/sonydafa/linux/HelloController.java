package com.sonydafa.linux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
public class HelloController {
    private static final Logger logger= LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){
        String ip=request.getRemoteAddr();
        logger.info("uri {}","/hello");
        logger.info("ip: {}",ip);
        return "hello";
    }
    @RequestMapping("/json")
    public @ResponseBody String json(){
        logger.info("uri {}","/json");
        return "json data";
    }
    @RequestMapping("/")
    public @ResponseBody String defaultPath(){
        logger.info("uri {}","/");
        return "default path";
    }

}
