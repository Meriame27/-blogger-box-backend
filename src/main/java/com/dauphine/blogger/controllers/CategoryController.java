package com.dauphine.blogger.controllers;
import com.dauphine.blogger.services.CategoryService;
import dto.CreationCategoryRequest;
import dto.CreationPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.net.URI;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false) String name) {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody String name) {
        Category category = categoryService.create(name);
        return ResponseEntity
                .created(URI.create("v1/categories/" + category.getId()))
                .body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryName(@PathVariable UUID id, @RequestBody String name) {
        try {
            Category updatedCategory = categoryService.updateName(id, name);
            if (updatedCategory == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating category: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) {
        try {
            boolean deleted = categoryService.deleteById(id);
            if (deleted) {
                return ResponseEntity.ok().body("Category deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting category: " + e.getMessage());
        }
    }
}
