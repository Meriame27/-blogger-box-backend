package com.dauphine.blogger.services;

import models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    Category getByID(UUID id);
    Category create(String name);
    Category updateName(UUID id, String name);
    UUID deleteById(UUID id);


}
