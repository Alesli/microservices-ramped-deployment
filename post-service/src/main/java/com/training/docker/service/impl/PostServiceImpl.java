package com.training.docker.service.impl;

import com.training.docker.configuration.UserServiceClient;
import com.training.docker.entity.Post;
import com.training.docker.entity.User;
import com.training.docker.repository.PostRepository;
import com.training.docker.service.PostService;
import com.training.docker.utils.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final UserServiceClient userServiceClient;

    @Override
    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Post create(Post post) {
        var authorId = post.getAuthorId();
        var author = userServiceClient.getUserById(authorId);
        if (author == null) {
            throw new UserNotFoundException(authorId);
        }
        userServiceClient.increaseAmountOfPosts(authorId);
        return repository.save(post);
    }

    @Override
    public Post update(Long id, Post post) {
        Optional<Post> existingPost = repository.findById(id);
        if (existingPost.isPresent()) {
            post.setId(existingPost.get().getId());
            return repository.save(post);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        var existingPost = repository.findById(id);
        if (existingPost.isPresent()) {
            userServiceClient.decreaseAmountOfPosts(existingPost.get().getAuthorId());
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
