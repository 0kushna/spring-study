package com.kt.spring_study.control;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.spring_study.dto.CommentDTO;
import com.kt.spring_study.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getcomments(@PathVariable("postId") Integer postId){
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@PathVariable("postId") Integer postId, @RequestBody CommentDTO commentDTO) {
        CommentDTO savedComment = commentService.addComment(postId, commentDTO);
        return ResponseEntity.ok(savedComment);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("commentId") Integer commentId, @RequestBody CommentDTO commentDTO){
        CommentDTO updatedComment = commentService.updateComment(commentId, commentDTO)
                        .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + commentId));
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Integer commentId){
        boolean deleted = commentService.deleteComment(commentId);
        return deleted ? ResponseEntity.ok("Comment deleted successfully") : ResponseEntity.notFound().build();
    }

    

}
