package com.kt.spring_study.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer>{

    List<Post> findAll();
    Post findById(int id);
    Post save(Post post);
    void deleteById(int id);
}