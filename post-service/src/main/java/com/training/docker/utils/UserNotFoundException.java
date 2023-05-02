package com.training.docker.utils;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long authorId) {
        super("User not found with id: " + authorId);
    }
}
