package com.training.docker.utils;

public class UserRequestFailure extends RuntimeException {

    public UserRequestFailure(String message) {
        super(message);
    }
}
