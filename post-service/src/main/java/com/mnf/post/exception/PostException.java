package com.mnf.post.exception;

public class PostException extends Exception {
    public static final String NOT_FOUND = "Entity not found";
    public static final String DATA_EXISTED = "Data existed";
    public PostException(String message) {
        super(message);
    }
}
