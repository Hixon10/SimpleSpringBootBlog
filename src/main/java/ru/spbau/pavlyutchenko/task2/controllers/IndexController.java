package ru.spbau.pavlyutchenko.task2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spbau.pavlyutchenko.task2.domain.Post;
import ru.spbau.pavlyutchenko.task2.service.PostRepository;

import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/post/search", method = RequestMethod.GET)
    public List<Post> searchPosts(@RequestParam String query) {
        return postRepository.search(query);
    }
}
