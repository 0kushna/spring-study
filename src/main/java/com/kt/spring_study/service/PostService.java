package com.kt.spring_study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kt.spring_study.dto.CommentDTO;
import com.kt.spring_study.dto.PostDTO;
import com.kt.spring_study.model.Post;
import com.kt.spring_study.model.PostRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private PostDTO convertToDTO(Post post){
        List<CommentDTO> commentDTOs = post.getComments().stream()
        .map(comment -> new CommentDTO(comment.getId(), post.getId(), comment.getContent(), comment.getAuthor()))
        .collect(Collectors.toList()); 

        return new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), commentDTOs);
    }

    private Post converToEntity(PostDTO postDTO){
        return new Post(postDTO.getId(), postDTO.getTitle(), postDTO.getContent(), postDTO.getAuthor(), new ArrayList<>());
    }

    public PostDTO addPost(PostDTO postDTO) {
        Post post = converToEntity(postDTO);
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    }

    public Optional<PostDTO> getOnePost(Integer postId) {
        return postRepository.findById(postId).map(this::convertToDTO);
     
    }

    @Transactional
    public Optional<PostDTO> updatePost(Integer postId, PostDTO postDTO) {
        return postRepository.findById(postId).map(existingPost -> {
            existingPost.setTitle(postDTO.getTitle());
            existingPost.setContent(postDTO.getContent());
            return convertToDTO(existingPost);
        });
    }

    public boolean deletePost(Integer id) {
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
            return true;
        }else return false;
    }

}
