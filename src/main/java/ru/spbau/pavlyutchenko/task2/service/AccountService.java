package ru.spbau.pavlyutchenko.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbau.pavlyutchenko.task2.domain.Account;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Boolean isValid(Account account) {
        List<Account> accounts = accountRepository.findAll();
        for (Account acc : accounts) {
            if (account.equals(acc)) {
                return true;
            }
        }

        return false;
    }
}
