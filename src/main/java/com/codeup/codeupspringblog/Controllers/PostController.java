package com.codeup.codeupspringblog.Controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }
    @GetMapping("/posts")
    public String showAllPosts(Model model) {
        List<Post> posts = new ArrayList<>();
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable String id, Model model) {
        Post post = postDao.getReferenceById(Long.valueOf(id));
        model.addAttribute("post", post);
        return "posts/show";
    }
    @GetMapping("posts/create")
    public String view() {
        return "posts/create";
    }

    @PostMapping("posts")
    public String create(@RequestParam String title, @RequestParam String description) {
        Post post = new Post( title, description);
        User user = userDao.getReferenceById(1L);

        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }
}
