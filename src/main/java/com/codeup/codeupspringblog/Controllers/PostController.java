package com.codeup.codeupspringblog.Controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String showAllPosts(Model model) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Title 1", "Body 1"));
        posts.add(new Post("Title 2", "Body 2"));
        model.addAttribute("posts", posts);
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable String id, Model model) {
        String title = "This is a title";
        String body = "This is a body";
        Post post = new Post(title, body);
        model.addAttribute("post", post);
        return "posts/show";
    }
    @GetMapping("posts/create")
    @ResponseBody
    public String view() {
        return "posts/create";
    }
    @PostMapping("posts/create")
    @ResponseBody
    public String create() {
        return "redirects:/posts";
    }
}
