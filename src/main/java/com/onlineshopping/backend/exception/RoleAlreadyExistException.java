package com.onlineshopping.backend.exception;

public class RoleAlreadyExistException extends RuntimeException {
    public RoleAlreadyExistException(String s) {
        super(s);
    }
}
