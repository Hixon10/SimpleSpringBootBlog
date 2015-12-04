package ru.spbau.pavlyutchenko.task2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbau.pavlyutchenko.task2.domain.Category;
import ru.spbau.pavlyutchenko.task2.domain.Post;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void create() {
//        Category category = new Category("category 1");
//        Post post = new Post("first post title", "content of post", category);
//
//        postRepository.save(post);
    }
}
