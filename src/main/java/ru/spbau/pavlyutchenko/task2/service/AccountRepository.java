package ru.spbau.pavlyutchenko.task2.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spbau.pavlyutchenko.task2.domain.Account;
import ru.spbau.pavlyutchenko.task2.domain.Post;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByLogin(String login);
}
