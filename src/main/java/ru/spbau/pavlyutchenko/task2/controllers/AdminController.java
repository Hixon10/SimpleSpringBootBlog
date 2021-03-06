package ru.spbau.pavlyutchenko.task2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spbau.pavlyutchenko.task2.domain.Account;
import ru.spbau.pavlyutchenko.task2.domain.Category;
import ru.spbau.pavlyutchenko.task2.domain.Post;
import ru.spbau.pavlyutchenko.task2.service.AccountRepository;
import ru.spbau.pavlyutchenko.task2.service.AccountService;
import ru.spbau.pavlyutchenko.task2.service.CategoryRepository;
import ru.spbau.pavlyutchenko.task2.service.PostRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/post/{login}/{password}", method=RequestMethod.PUT)
    public void editPost(@PathVariable("login") String login, @PathVariable("password") String password,
                         @RequestParam Long categoryId, @RequestParam Long postId, @RequestBody @Valid Post post) {
        Account account = new Account(login, password);
        if (!accountService.isValid(account)) {
            throw new IllegalArgumentException("Account with login " + account.getLogin() + " not found.");
        }

        Post updatePost = postRepository.findOne(postId);
        Category category = categoryRepository.findOne(categoryId);

        updatePost.setTitle(post.getTitle());
        updatePost.setContent(post.getContent());
        updatePost.setCategory(category);

        postRepository.save(updatePost);
    }

    @RequestMapping(value = "/post/{login}/{password}", method = RequestMethod.POST)
    public void createPost(@PathVariable("login") String login, @PathVariable("password") String password,
                           @RequestParam Long categoryId, @RequestBody @Valid Post post) {
        Optional<Account> account = accountRepository.findByLogin(login);
        if (!account.isPresent() || !accountService.isValid(account.get())) {
            throw new IllegalArgumentException("Account with login " + login + " not found.");
        }

        Category category = categoryRepository.findOne(categoryId);
        post.setCategory(category);
        post.setAccount(account.get());
        post.setCreatedDate(LocalDateTime.now());

        postRepository.save(post);
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @RequestMapping(value = "/post/{login}/{password}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable("login") String login, @PathVariable("password") String password, @RequestParam Long postId) {
        Account account = new Account(login, password);
        if (!accountService.isValid(account)) {
            throw new IllegalArgumentException("Account with login " + account.getLogin() + " not found.");
        }

        postRepository.delete(postId);
    }

    @RequestMapping(value = "/category/{login}/{password}", method = RequestMethod.POST)
    public void createCategory(@PathVariable("login") String login, @PathVariable("password") String password, @RequestBody @Valid Category category) {
        Account account = new Account(login, password);
        if (!accountService.isValid(account)) {
            throw new IllegalArgumentException("Account with login " + account.getLogin() + " not found.");
        }

        categoryRepository.save(category);
    }

    @RequestMapping(value = "/category/{login}/{password}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("login") String login, @PathVariable("password") String password, @RequestParam Long categoryId) {
        Account account = new Account(login, password);
        if (!accountService.isValid(account)) {
            throw new IllegalArgumentException("Account with login " + account.getLogin() + " not found.");
        }

        categoryRepository.delete(categoryId);
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @RequestMapping(value = "/category/{login}/{password}", method=RequestMethod.PUT)
    public void editCategory(@PathVariable("login") String login, @PathVariable("password") String password,
                             @RequestParam Long categoryId, @RequestBody @Valid Category category) {
        Account account = new Account(login, password);
        if (!accountService.isValid(account)) {
            throw new IllegalArgumentException("Account with login " + account.getLogin() + " not found.");
        }

        Category updateCategory = categoryRepository.findOne(categoryId);
        updateCategory.setTitle(category.getTitle());
        categoryRepository.save(updateCategory);
    }
}
