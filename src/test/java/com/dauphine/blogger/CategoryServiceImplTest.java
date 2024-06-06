package com.dauphine.blogger;

import com.dauphine.blogger.exceptions.CategoryNotFoundexception;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.impl.CategoryServiceImpl;
import models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void getAllCategories() {
        Category category1 = new Category("Category1");
        Category category2 = new Category("Category2");
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.getAll();

        assertEquals(2, categories.size());
        verify(categoryRepository).findAll();
    }

    @Test
    public void getAllCategoriesByName() {
        String name = "Category";
        Category category = new Category(name);
        when(categoryRepository.findByName(name)).thenReturn(Arrays.asList(category));

        List<Category> categories = categoryService.getAllByName(name);

        assertEquals(1, categories.size());
        verify(categoryRepository).findByName(name);
    }

    @Test
    public void getCategoryByIdWhenIdExists() {
        UUID id = UUID.randomUUID();
        Category expected = new Category("Category");
        when(categoryRepository.findById(id)).thenReturn(Optional.of(expected));

        Category actual = categoryService.getById(id);

        assertEquals(expected, actual);
        verify(categoryRepository).findById(id);
    }

    @Test
    public void getCategoryByIdWhenIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CategoryNotFoundexception.class, () -> {
            categoryService.getById(id);
        });

        assertEquals("Category with id " + id + " not found", exception.getMessage());
    }

    @Test
    public void createCategory() {
        String name = "New Category";
        Category savedCategory = new Category(name);
        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        Category result = categoryService.create(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    public void updateCategoryName() {
        UUID id = UUID.randomUUID();
        String newName = "Updated Name";
        Category existingCategory = new Category("Old Name");
        existingCategory.setId(id);
        when(categoryRepository.findById(id)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(existingCategory)).thenReturn(existingCategory);

        Category updatedCategory = categoryService.updateName(id, newName);

        assertEquals(newName, updatedCategory.getName());
        verify(categoryRepository).save(existingCategory);
    }

}
