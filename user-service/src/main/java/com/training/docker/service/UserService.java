package com.training.docker.service;

import com.training.docker.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User create(User user);

    User update(Long id, User user);

    boolean deleteById(Long id);

    boolean increaseAmountOfPosts(Long userId);

    boolean decreaseAmountOfPosts(Long userId);
}
