package ru.spbau.pavlyutchenko.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbau.pavlyutchenko.task2.domain.Category;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(String title) {
        Category category = new Category(title);
        return categoryRepository.save(category);
    }

    public Category findByTitle(String title) {
        Optional<Category> category = categoryRepository.findByTitle(title);
        return category.orElseThrow(() -> new IllegalArgumentException("Category with title " + title + " not found."));
    }
}

