package com.onlineshopping.backend.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String s) {
        super(s);
    }
}
