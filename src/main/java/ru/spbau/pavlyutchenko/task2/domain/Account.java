package ru.spbau.pavlyutchenko.task2.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Account {

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    Account() {}

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "account")
    private Set<Post> posts = new HashSet<>();

    @NotNull
    @Size(min=3)
    @Column(unique = true, nullable = false)
    private String login;

    @NotNull
    @Size(min=3)
    @Column(unique = true, nullable = false)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!login.equals(account.login)) return false;
        return password.equals(account.password);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
