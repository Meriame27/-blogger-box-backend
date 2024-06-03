package com.dauphine.blogger.exceptions;

public class CategoryNameAlreadyExistsException extends Exception {
    public CategoryNameAlreadyExistsException(){
        super("The name of the category already exists");
    }
}
