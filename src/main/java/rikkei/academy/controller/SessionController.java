package rikkei.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.User;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
@SessionAttributes("userlogin")
public class SessionController {
    @ModelAttribute("user")
    public User setUpUserForm() { return new User(); }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/dologin")
    public String dologin(@ModelAttribute("user") User user, Model model, HttpSession httpSession){
        if (user.getEmail().equals("hung@gmail.com")&& user.password.equals("123456")){
            model.addAttribute("message","login succsess");
            httpSession.setAttribute("userlogin",user);
        }else {
            model.addAttribute("message","login fail");
        }
        return "login";
    }
}
