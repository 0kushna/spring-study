package com.kt.spring_study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kt.spring_study.model.Post;
import com.kt.spring_study.model.PostRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getOnePost(Integer id) {
        return postRepository.findById(id);
     
    }

    @Transactional
    public boolean updatePost(Integer id, Post post) {
        Post existPost = postRepository.findById(id).orElse(null);
        if (existPost != null) {
            existPost.setTitle(post.getTitle());
            existPost.setContent(post.getContent());
            // postRepository.save(existPost);
            return true;
        } else
            return false;
    }

    public boolean deletePost(Integer id) {
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
            return true;
        }else return false;
    }

}
