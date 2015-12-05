package ru.spbau.pavlyutchenko.task2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spbau.pavlyutchenko.task2.domain.Category;
import ru.spbau.pavlyutchenko.task2.service.CategoryRepository;
import ru.spbau.pavlyutchenko.task2.service.CategoryService;
import ru.spbau.pavlyutchenko.task2.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public void createCategory(@RequestBody @Valid Category category) {
        categoryRepository.save(category);
    }

    @RequestMapping(value = "/category", method = RequestMethod.DELETE)
    public void deleteCategory(@RequestParam Long categoryId) {
        categoryRepository.delete(categoryId);
    }
}
