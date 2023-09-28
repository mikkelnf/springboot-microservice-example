package com.mnf.user.exception;

public class UserException extends Exception {
    public static final String NOT_FOUND = "User not found";
    public static final String USERNAME_EXISTED = "Username is already taken";
    public UserException(String message) {
        super(message);
    }
}
