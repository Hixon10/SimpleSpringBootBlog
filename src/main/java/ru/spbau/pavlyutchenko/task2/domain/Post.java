package ru.spbau.pavlyutchenko.task2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Post {

    public Post(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    Post() {}

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=5)
    private String title;

    @NotNull
    @Size(min=10)
    private String content;

    //    @JsonIgnore
    @ManyToOne
    private Category category;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
