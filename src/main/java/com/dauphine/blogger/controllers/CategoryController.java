package com.dauphine.blogger.controllers;
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
    private final List<Category> temporaryCategories;

    public CategoryController() {
        temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));

    }
@GetMapping
@Operation(
        summary = "Retrieve all categories",
        description = ""
)
    public List<Category> retrieveAllCategories(){
        return temporaryCategories;
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a category by id",
            description = "Returns '{id}' by path variable"
    )
    public String retrieveCategoryByID(
            @Parameter(description = "ID of the cataegory")
            @PathVariable UUID id
    ) {
        return "Category " + id;
    }

    @PostMapping("")
    @Operation(
            summary = "Create a category",
            description = ""
    )
    public String createCategory(@RequestBody CreationCategoryRequest category) {
        return "Creating a new category";
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a category",
            description = ""
    )
    public String updateCategory(@PathVariable UUID id, @RequestBody CreationCategoryRequest category) {
        //TODO later...
        return "Update the noun of the category.";
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a category",
            description = ""
    )
    public String deleteCategory(@PathVariable UUID id){
        return "Delete a category";

    }





}




