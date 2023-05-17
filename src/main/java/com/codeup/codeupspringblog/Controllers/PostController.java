package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String post() {
        return "posts index page";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String id(@PathVariable("id") String id) {
        return id;
    }
    @GetMapping("posts/create")
    @ResponseBody
    public String view() {
        return "view the form for creating a post";
    }
    @GetMapping("posts/create")
    @ResponseBody
    public String create() {
        return "create a post";
    }
}
