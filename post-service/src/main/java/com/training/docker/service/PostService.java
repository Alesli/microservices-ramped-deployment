package com.training.docker.service;

import com.training.docker.entity.Post;
import com.training.docker.entity.User;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post create(Post post);

    Post update(Long id, Post post);

    boolean deleteById(Long id);
}
