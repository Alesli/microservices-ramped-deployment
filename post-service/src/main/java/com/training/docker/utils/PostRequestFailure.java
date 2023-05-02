package com.training.docker.utils;

public class PostRequestFailure extends RuntimeException {

    public PostRequestFailure(String message) {
        super(message);
    }
}
