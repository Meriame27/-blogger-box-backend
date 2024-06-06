package com.dauphine.blogger;

import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import com.dauphine.blogger.services.impl.PostServiceImpl;
import models.Category;
import models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostServiceImplTest {

    private PostRepository postRepository;
    private PostService postService;
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;
    @BeforeEach
    public void setup() {
        postRepository = mock(PostRepository.class);
        postService = new PostServiceImpl(postRepository,categoryRepository );
    }

    @Test
    public void getAllPostsByCategoryId() {
        Category category1 = new Category("Category1");
        Post post1 = new Post("Title1", "Content1", category1);
        Post post2 = new Post("Title2", "Content2", category1);
        when(postRepository.findAllByCategoryId(category1.getId())).thenReturn(Arrays.asList(post1, post2));

        List<Post> posts = postService.getAllByCategoryId(category1.getId());

        assertEquals(2, posts.size());
        verify(postRepository).findAllByCategoryId(category1.getId());
    }

    @Test
    public void getAllPosts() {
        Category category1 = new Category("Category1");
        Post post1 = new Post("Title1", "Content1", category1);
        Post post2 = new Post("Title2", "Content2", category1);
        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2));

        List<Post> posts = postService.getAll();

        assertEquals(2, posts.size());
        verify(postRepository).findAll();
    }

    @Test
    public void getPostByIdWhenExists() {
        Category category1 = new Category("Category1");
        Post expected = new Post("Title", "Content", category1);
        when(postRepository.findById(category1.getId())).thenReturn(Optional.of(expected));

        Post actual = postService.getById(category1.getId());

        assertEquals(expected, actual);
        verify(postRepository).findById(category1.getId());
    }

    @Test
    public void getPostByIdWhenDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(PostNotFoundByIdException.class, () -> {
            postService.getById(id);
        });

        assertEquals("Post with id " + id + " not found", exception.getMessage());
    }

    @Test
    public void createPost() {
        Category category1 = new Category("Category1");
        String title = "New Post";
        String content = "Content of new post";
        UUID categoryId = UUID.randomUUID();
        Post savedPost = new Post(title, content, category1);
        when(postRepository.save(any(Post.class))).thenReturn(savedPost);

        Post result = postService.create(title, content, categoryId);

        assertNotNull(result);
        assertEquals(title, result.getTitle());
        assertEquals(content, result.getContent());
        verify(postRepository).save(any(Post.class));
    }

    @Test
    public void updatePost() {
        Category category1 = new Category("Category1");
        String newTitle = "Updated Title";
        String newContent = "Updated Content";
        Post existingPost = new Post("Old Title", "Old Content", category1);
        existingPost.setId(category1.getId());
        when(postRepository.findById(category1.getId())).thenReturn(Optional.of(existingPost));
        when(postRepository.save(existingPost)).thenReturn(existingPost);

        Post updatedPost = postService.update(category1.getId(), newTitle, newContent);

        assertEquals(newTitle, updatedPost.getTitle());
        assertEquals(newContent, updatedPost.getContent());
        verify(postRepository).save(existingPost);
    }

}
