package com.lrm.blog.web.admin;

import com.lrm.blog.po.User;
import com.lrm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){

        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user =userService.checkUser(username,password);
        if(user != null){
            //不要把密碼傳到前面去
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","帳號或密碼錯誤");
            //不要直接"admin/login" 他會直接回到頁面但是直接登陸會有問題
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public  String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
