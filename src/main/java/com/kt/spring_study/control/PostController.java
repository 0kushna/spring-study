package com.kt.spring_study.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.spring_study.dto.PostDTO;
import com.kt.spring_study.model.Post;
import com.kt.spring_study.service.PostService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO) {
        PostDTO savedPost = postService.addPost(postDTO);
        return ResponseEntity.ok(savedPost);
    }
    
    @GetMapping("")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
    
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getOnePost(@PathVariable("postId") Integer postId) {
        return postService.getOnePost(postId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @PatchMapping("/{postId}")
    public ResponseEntity<PostDTO> update(@PathVariable("postId") Integer postId, @RequestBody PostDTO postDTO){
           return postService.updatePost(postId, postDTO)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> delete(@PathVariable("postId") Integer postId){
        if(postService.deletePost(postId)){
            return ResponseEntity.ok("Post deleted");
        }
          else return ResponseEntity.notFound().build();

    }
    
}
