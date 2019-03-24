package spittle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping("/spittle")
public class SpittleController {
    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String goHome(){
        return "home";
    }
    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String showRegisterForm(){
        return "registerForm";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegisterForm(Spitter spitter, RedirectAttributes model){
        model.addFlashAttribute("spitter",spitter);
        return "redirect:/spittle/profile";
    }
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String showProfile(Model model){
        return "profile";
    }
    @RequestMapping(value="/upload",method = RequestMethod.GET)
    public String showUploadForm(){
        return "uploadForm";
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String processUploadForm(HttpServletRequest request)
    throws IOException{
        request.setCharacterEncoding("UTF-8");
        StandardServletMultipartResolver cmr = new StandardServletMultipartResolver();
        if(cmr.isMultipart(request)){
            MultipartHttpServletRequest mhs = cmr.resolveMultipart(request);
            MultipartFile mf = mhs.getFile("profilePicture");
            String root="C:\\Users\\geoge\\IdeaProjects\\Spring_Maven\\src\\main\\webapp\\tmp\\upload\\";
            root+=mf.getOriginalFilename();
            mf.transferTo(new File(root));
        }
        return "profile";
    }

}