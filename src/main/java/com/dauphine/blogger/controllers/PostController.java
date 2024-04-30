package com.dauphine.blogger.controllers;

import com.dauphine.blogger.services.PostService;
import dto.CreationPostRequest;
import dto.UpdatePostRequest;
import models.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public List<Post> getAllPosts(@RequestParam(required = false) String value){
        return value == null || value.isBlank() ? postService.getAll() : postService.getAllByTitleOrContent(value) ;
    }

    @GetMapping("/{categoryId}")
    public List<Post> getPostsByCategoryId(@PathVariable UUID categoryId){
        return postService.getAllByCategoryId(categoryId);
    }

    @PostMapping("")
    public <CreatePostRequestBody> Post createPost(@RequestBody CreationPostRequest createPostRequestBody){
        return postService.create(createPostRequestBody.getTitle(), createPostRequestBody.getContent(), createPostRequestBody.getCategoryId());
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable UUID id, UpdatePostRequest updatePostRequestBody){
        return postService.update(id, updatePostRequestBody.getTitle(), updatePostRequestBody.getContent());
    }

    @DeleteMapping("/{id}")
    public UUID deletePost(@PathVariable UUID id){
        return postService.deleteById(id)?id:null;
    }
}