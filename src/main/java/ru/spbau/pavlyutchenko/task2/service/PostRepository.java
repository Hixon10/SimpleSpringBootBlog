package ru.spbau.pavlyutchenko.task2.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.spbau.pavlyutchenko.task2.domain.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);

    @Query(
            value = "SELECT * FROM post WHERE title LIKE %:searchTerm%",
            nativeQuery = true
    )
    public List<Post> search(@Param("searchTerm") String searchTerm);
}
