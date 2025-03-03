package com.kt.spring_study.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kt.spring_study.dto.CommentDTO;
import com.kt.spring_study.model.Comment;
import com.kt.spring_study.model.CommentRepository;
import com.kt.spring_study.model.Post;
import com.kt.spring_study.model.PostRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private CommentDTO convertToDTO(Comment comment){
        return new CommentDTO(comment.getId(), comment.getPost().getId(), 
                            comment.getContent(), comment.getAuthor());
    }

    private Comment convertToEntity(CommentDTO commentDTO, Post post){
        return new Comment(commentDTO.getId(), post, commentDTO.getContent(), commentDTO.getAuthor());
    }

   public CommentDTO addComment(Integer postId, CommentDTO commentDTO){
        Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("Post not found with ID " + postId));
           
            Comment comment = convertToEntity(commentDTO, post);
            Comment savedComment = commentRepository.save(comment);
            return convertToDTO(savedComment);

   }
   
   public List<CommentDTO> getComments(Integer postId){
        return commentRepository.findByPostId(postId)
                                .stream()
                                .map(this::convertToDTO)
                                .collect(Collectors.toList());
   }

   @Transactional
   public Optional<CommentDTO> updateComment(Integer commentId, CommentDTO commentDTO){
        return commentRepository.findById(commentId).map(comment -> {
            comment.setContent(commentDTO.getContent());
            commentRepository.save(comment);
            return convertToDTO(comment);
        });
        
           
   }

  public boolean deleteComment(Integer commentId){
        if(commentRepository.existsById(commentId)){
            commentRepository.deleteById(commentId);
            return true;
        }
        else return false;  
    }



}
