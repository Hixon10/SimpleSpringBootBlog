package ru.spbau.pavlyutchenko.task2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spbau.pavlyutchenko.task2.domain.Category;
import ru.spbau.pavlyutchenko.task2.domain.Post;
import ru.spbau.pavlyutchenko.task2.service.CategoryRepository;
import ru.spbau.pavlyutchenko.task2.service.CategoryService;
import ru.spbau.pavlyutchenko.task2.service.PostRepository;
import ru.spbau.pavlyutchenko.task2.service.PostService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/post", method=RequestMethod.PUT)
    public void editPost(@RequestParam Long categoryId, @RequestParam Long postId, @RequestBody @Valid Post post) {
        Post updatePost = postRepository.findOne(postId);
        Category category = categoryRepository.findOne(categoryId);

        updatePost.setTitle(post.getTitle());
        updatePost.setContent(post.getContent());
        updatePost.setCategory(category);

        postRepository.save(updatePost);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void createPost(@RequestParam Long categoryId, @RequestBody @Valid Post post) {
        Category category = categoryRepository.findOne(categoryId);
        post.setCategory(category);
        post.setCreatedDate(LocalDateTime.now());

        postRepository.save(post);
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @RequestMapping(value = "/post", method = RequestMethod.DELETE)
    public void deletePost(@RequestParam Long postId) {
        postRepository.delete(postId);
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public void createCategory(@RequestBody @Valid Category category) {
        categoryRepository.save(category);
    }

    @RequestMapping(value = "/category", method = RequestMethod.DELETE)
    public void deleteCategory(@RequestParam Long categoryId) {
        categoryRepository.delete(categoryId);
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @RequestMapping(value = "/category", method=RequestMethod.PUT)
    public void editCategory(@RequestParam Long categoryId, @RequestBody @Valid Category category) {
        Category updateCategory = categoryRepository.findOne(categoryId);
        updateCategory.setTitle(category.getTitle());
        categoryRepository.save(updateCategory);
    }
}
