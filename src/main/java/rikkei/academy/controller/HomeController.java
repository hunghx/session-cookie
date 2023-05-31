package rikkei.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping("/")
    public  String home(@CookieValue(value = "username", defaultValue = "") String username,HttpServletResponse response){
        Cookie cookie = new Cookie("username",username);
        cookie.setMaxAge(5*60);
        response.addCookie(cookie);
        return "index";
    }
    @GetMapping("/cookie")
    public String cookie(HttpServletRequest request,HttpServletResponse response, Model model){

      Cookie[] cookies= null;
      cookies= request.getCookies();
        for (Cookie c: cookies) {
            if (c.getName().equals("username")){
                c.setMaxAge(3);
                response.addCookie(c);
            }
        }
        return "cookie";
    }
    @RequestMapping("/counter")
    public  String count(@CookieValue(value = "count",  defaultValue = "0") Long count, HttpServletRequest request, HttpServletResponse response, Model model){
        count++;
        Cookie cookie = new Cookie("count",count.toString());
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);

        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie c:cookies
                 ) {
                if (c.getName().equals("count")){
                    model.addAttribute("count",c);
                }
            }
        }
        return "count";
    }
}
