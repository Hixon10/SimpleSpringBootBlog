package ru.spbau.pavlyutchenko.task2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spbau.pavlyutchenko.task2.domain.Account;
import ru.spbau.pavlyutchenko.task2.domain.Category;
import ru.spbau.pavlyutchenko.task2.domain.Post;
import ru.spbau.pavlyutchenko.task2.service.AccountRepository;
import ru.spbau.pavlyutchenko.task2.service.AccountService;
import ru.spbau.pavlyutchenko.task2.service.CategoryRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody @Valid Account account) {
        Optional<Account> oldAcc = accountRepository.findByLogin(account.getLogin());
        if (oldAcc.isPresent()) {
            throw new IllegalArgumentException("User with login " + account.getLogin() + " has already existed.");
        }

        accountRepository.save(account);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody @Valid Account account) {
        if (!accountService.isValid(account)) {
            throw new IllegalArgumentException("Account with login " + account.getLogin() + " not found.");
        }
    }
}
