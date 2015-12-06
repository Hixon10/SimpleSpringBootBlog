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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void createPost(@RequestParam Long categoryId, @RequestBody @Valid Post post) {
        Category category = categoryRepository.findOne(categoryId);
        post.setCategory(category);

        postRepository.save(post);
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
