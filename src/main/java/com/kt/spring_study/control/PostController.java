package com.kt.spring_study.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.spring_study.model.Post;
import com.kt.spring_study.service.PostService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

   
    private final PostService postService;


    // public PostController(PostService postService){
    //     this.postService = postService;
    // }

    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }
    
    @GetMapping("")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Post> getOnePost(@PathVariable("id") Integer id) {
        Optional<Post> post = postService.getOnePost(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable("id") Integer id, @RequestBody Post post){
           if(postService.updatePost(id, post)){
             return ResponseEntity.ok().build();
           }
             else return ResponseEntity.notFound().build();
      
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        if(postService.deletePost(id)){
            return ResponseEntity.noContent().build();
        }
          else return ResponseEntity.notFound().build();

    }
    
}
