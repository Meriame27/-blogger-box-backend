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
        Category category1 = new Category(UUID.randomUUID(), "Category1");
        Category category2 = new Category(UUID.randomUUID(), "Category2");
        Category category3 = new Category(UUID.randomUUID(), "Category3");

        temporaryPosts.add(new Post(UUID.randomUUID(), "my first post", "category1");
        temporaryPosts.add(new Post(UUID.randomUUID(), "my second post", "category2");
        temporaryPosts.add(new Post(UUID.randomUUID(), "my third post", "category3");
    }
    public List<Post> getAllByCategoryId(UUID categoryId){
        List<Post> postByCategory = new ArrayList<>();
        for(Post p : temporaryPosts){
            if (p.getCategoryId()==categoryId){
                postByCategory.add(p);
            }
        }


        }

    }
    public List<Post> getAll(){
        return this.temporaryPosts;
    }
    public Post getById(UUID id){
        return temporaryPosts.stream().filter(post -> id.equals(post.getId()));
    }

    public Post create(String title, String content, UUID categoryId){
        Post post = new Post("",content, new Category(categoryId));
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
