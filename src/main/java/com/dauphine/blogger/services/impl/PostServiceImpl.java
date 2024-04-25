package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.services.PostService;
import models.Category;
import models.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostServiceImpl implements PostService {
    private final List<Post> temporaryPosts;
    public PostServiceImpl() {
        temporaryPosts = new ArrayList<>();
        Object categorie = null;
        temporaryPosts.add(new Post(UUID.randomUUID(), Category categorie, "my first post"));
        temporaryPosts.add(new Post(UUID.randomUUID(), Category categorie, "my second post"));
        temporaryPosts.add(new Post(UUID.randomUUID(), Category categorie, "my third post"));
    }
    public List<Post> getAllByCategoryId(UUID categoryId){
        return temporaryCategories.stream()
                .filter(category -> id.equals(category.getId()))
                .findFirst()
                .orElse(null);;
    }
    public List<Post> getAll(){
        return null;
    }
    /*public Post getById(UUID id){
        return temporaryPosts;
    }

     */
    public Post create(String title, String content, UUID categoryId){
        Post post = new Post(UUID.randomUUID(),new Category(categoryId),content);
        temporaryPosts.add(post);
        return post;
    }
    public Post update(UUID id, String title, String content){
        Post post = temporaryPosts.stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .orElse(null);
        if (post != null){
            post.setName(newName);
    }
    public boolean deleteById(UUID id){
        return true;
    }
}
