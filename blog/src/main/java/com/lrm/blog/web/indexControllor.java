package com.lrm.blog.web;

import com.lrm.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class indexControllor {

    @GetMapping("/")
    public String index() {

//        String blog  = null;
//
//        if(blog == null){
//            throw new NotFoundException("此篇文章已經不存在了");
//        }
//        System.out.println("--index---");
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
}
