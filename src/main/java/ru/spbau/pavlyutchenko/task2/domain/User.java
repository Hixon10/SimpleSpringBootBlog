package ru.spbau.pavlyutchenko.task2.domain;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User {

    User() {}

    @Id
    @GeneratedValue
    private Long id;
}
