package org.example;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller {


    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        System.out.println("hello Controller");
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "Hello Spring MVC");
        mv.setViewName("hello"); //WEB-INF/jsp/hello.jsp
        return mv;
    }
}
