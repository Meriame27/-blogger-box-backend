package com.dauphine.blogger.controllers;

import dto.CreationPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import models.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final List<Post> temporaryPosts;

    public PostController() {
        temporaryPosts = new ArrayList<>();
        temporaryPosts.add(new Post("my first post"));
        temporaryPosts.add(new Post("my second post"));
        temporaryPosts.add(new Post("my third post"));

    }

    @PostMapping("")
    @Operation(
            summary = "Create a post",
            description = ""
    )
    public String createPost(@RequestBody CreationPostRequest post) {
        return "Creating a new post";
    }

    @DeleteMapping("")
    @Operation(
            summary = "Delete a post",
            description = ""
    )
    public String deletePost(){
        return "Delete a post";

    }

    @GetMapping
    @Operation(
            summary = "Retrieve all posts ordered by creation date",
            description = ""
    )
    public List<Post> retrieveAllPostsByDate(){
        return temporaryPosts;
    }
    /*@GetMapping("")
    @Operation(
            summary = "Retrieve all posts per a category",
            description = ""
    )
    public String retrieveAllPostsPerCategory(
            //@Parameter(description = "")
            //@PathVariable UUID id
    ) {
        return "post";
    }
*/
}
