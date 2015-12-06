package ru.spbau.pavlyutchenko.task2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    public Category(String title) {
        this.title = title;
    }

    Category() {}

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "category")
    private Set<Post> posts = new HashSet<>();

    @NotNull
    @Size(min=5)
    @Column(unique = true, nullable = false)
    private String title;

    public Set<Post> getPosts() {
        return posts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }
}
