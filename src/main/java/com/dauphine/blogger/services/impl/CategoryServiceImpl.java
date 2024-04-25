package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import models.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    public CategoryServiceImpl(CategoryRepository repository){
        this.repository = repository;
    }
    /*
    private final List<Category> temporaryCategories;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
        temporaryCategories = new ArrayList<>();
      temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
      temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
      temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }
    */


    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getByID(UUID id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(name);
        return repository.save(category);
    }

    @Override
    public Category updateName(UUID id, String newName) {
        Category category = getByID(id);
        if (category ==null){
            return null;
        }
        category.setName(newName);
        return repository.save(category);
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }

}
