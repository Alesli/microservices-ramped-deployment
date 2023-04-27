package com.training.docker.configuration;

import com.training.docker.entity.User;
import org.springframework.web.client.RestTemplate;

public class UserServiceClient {

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    public UserServiceClient(RestTemplate restTemplate, String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }

    public User getUserById(Long userId) {
        String url = userServiceUrl + "/users/{userId}";
        return restTemplate.getForObject(url, User.class, userId);
    }

    public void increaseAmountOfPosts(Long userId) {
        String url = userServiceUrl + "/users/{id}/increment";
        restTemplate.postForLocation(url, Void.class, userId);
    }

    public void decreaseAmountOfPosts(Long userId) {
        String url = userServiceUrl + "/users/{id}/decrement";
        restTemplate.postForLocation(url, Void.class, userId);
    }
}
