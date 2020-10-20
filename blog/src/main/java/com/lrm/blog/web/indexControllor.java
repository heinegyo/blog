package com.lrm.blog.web;

import com.lrm.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class indexControllor {

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
    
}
