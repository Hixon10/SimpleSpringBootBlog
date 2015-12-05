package ru.spbau.pavlyutchenko.task2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spbau.pavlyutchenko.task2.domain.Category;
import ru.spbau.pavlyutchenko.task2.service.CategoryService;
import ru.spbau.pavlyutchenko.task2.service.PostService;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        categoryService.create("category 3");
        Long val = counter.incrementAndGet();
        Category category = categoryService.findByTitle("category 2");
        return "10" + val.toString() + name + category.getTitle();
    }

}
