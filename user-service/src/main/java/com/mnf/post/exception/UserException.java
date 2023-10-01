package com.mnf.post.exception;

public class UserException extends Exception {
    public static final String NOT_FOUND = "Entity not found";
    public static final String DATA_EXISTED = "Data existed";
    public UserException(String message) {
        super(message);
    }
}
