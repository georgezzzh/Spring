package spittr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
    @Autowired
    @Qualifier("spittleRepository1")
    private SpittleRepository spittleRepository;
    //读取请求参数
    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String showRegistractionForm(){
        return "registerForm";
    }
    @RequestMapping(value="/register",method = RequestMethod.POST)
    //验证输入是否有效
    public String processRegistraction(Spitter spitter){
        System.out.println(spitter.toString());
        //重定向到基本信息
        return "redirect:/spittles/"+spitter.getUsername();
    }
    //一开始智障了，有两个URL模式是相同的，一直出现类型不匹配。。
    @RequestMapping(value="/{username}",method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable("username") String username,Model model){
        model.addAttribute("name",username);
        return "profile";
    }
}
