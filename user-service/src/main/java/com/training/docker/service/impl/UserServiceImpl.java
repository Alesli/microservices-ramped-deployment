package com.training.docker.service.impl;

import com.training.docker.entity.User;
import com.training.docker.repository.UserRepository;
import com.training.docker.service.UserService;
import com.training.docker.utils.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> existingUser = repository.findById(id);
        if (existingUser.isPresent()) {
            user.setId(existingUser.get().getId());
            return repository.save(user);
        }
        throw new UserNotFoundException(id);
    }

    @Override
    public boolean deleteById(Long id) {
        var existingUser = repository.findById(id);
        if (existingUser.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public boolean increaseAmountOfPosts(Long userId) {
        return updateAmountOfPosts(userId, 1);
    }

    @Override
    public boolean decreaseAmountOfPosts(Long userId) {
        return updateAmountOfPosts(userId, -1);
    }

    private boolean updateAmountOfPosts(final Long userId, int counter) {
        var existingUserOpt = repository.findById(userId);
        if (existingUserOpt.isPresent()) {
            var existingUser = existingUserOpt.get();
            final long amountOfPosts = existingUser.getAmountOfPosts() + counter;
            if (amountOfPosts >= 0) {
                existingUser.setAmountOfPosts(amountOfPosts);
                repository.save(existingUser);
                return true;
            }
        }
        return false;
    }
}
