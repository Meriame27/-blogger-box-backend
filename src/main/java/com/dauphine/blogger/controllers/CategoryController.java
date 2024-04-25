package com.dauphine.blogger.controllers;
import com.dauphine.blogger.services.CategoryService;
import dto.CreationCategoryRequest;
import dto.CreationPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import models.Category;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryService service;
    public CategoryController(CategoryService service){
        this.service = service;
    }


@GetMapping
@Operation(
        summary = "Retrieve all categories",
        description = ""
)
    public List<Category> retrieveAllCategories(){
        return service.getAll();
    }
    @GetMapping("{id}")
    @Operation(
            summary = "Retrieve a category by id",
            description = "Returns '{id}' by path variable"
    )
    public Category retrieveCategoryByID(
            @Parameter(description = "ID of the category")
            @PathVariable UUID id
    ) {
        return service.getByID(id);
    }

    @PostMapping("")
    @Operation(
            summary = "Create a category",
            description = ""
    )
    public Category createCategory(@RequestBody String name) {
        return service.create(name);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update a category",
            description = ""
    )
    public Category updateCategory(@PathVariable UUID id, @RequestBody String name) {
        return service.updateName(id,name);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a category",
            description = ""
    )
    public UUID deleteCategory(@PathVariable UUID id){
        return service.deleteById(id);

    }





}




