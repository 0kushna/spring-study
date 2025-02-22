package com.kt.spring_study.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.spring_study.model.Post;
import com.kt.spring_study.service.PostService;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }
    
    @GetMapping("")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    @GetMapping("/{id}")
    public Post getOnePost(@PathVariable("id") Integer id) {
        return postService.getOnePost(id);
    }

    @Transactional
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Integer id, @RequestBody Post post){
        String title = post.getTitle();
        String content = post.getContent();
        postService.updatePost(id, title, content);
        return "update post";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        postService.deletePost(id);
    }
    
}
