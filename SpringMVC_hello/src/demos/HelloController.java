package demos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {
    /*
    * 可以同时映射多个值
    * */
    @RequestMapping(value={"hello","home"},method= RequestMethod.GET)
    public String printWelcome(ModelMap model){
    model.addAttribute("message","hello world");
    return "hello";
    }
    @RequestMapping(value="/page/{name}/{age}",method = RequestMethod.GET)
    public String getName(ModelMap map, @PathVariable String name, @PathVariable int age){
         map.addAttribute("name",name);
         map.addAttribute("age",age);
         return "name";
    }
}
