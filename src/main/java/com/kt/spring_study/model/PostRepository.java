package com.kt.spring_study.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

    List<Post> findAll();
    Optional<Post> findById(int id);
    Post save(Post post);
    void deleteById(int id);
}