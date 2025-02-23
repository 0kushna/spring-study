package com.kt.spring_study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.spring_study.model.Post;
import com.kt.spring_study.model.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    private List<Post> posts = new ArrayList<>();
    private int nextId = 1;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post addPost(Post post){
        post.setId(nextId++);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
       return postRepository.findAll();
    }

    public Post getOnePost(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post updatePost(Integer id, String title, String content) {
        Post post = postRepository.findById(id).orElse(null);
        post.setTitle(title);
        post.setContent(content);
        return post;

    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }


}
