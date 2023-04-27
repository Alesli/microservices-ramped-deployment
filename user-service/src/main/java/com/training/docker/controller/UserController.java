package com.training.docker.controller;

import com.training.docker.entity.User;
import com.training.docker.service.UserService;
import com.training.docker.utils.UserRequestFailure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        var user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllUsers() {
        var users = userService.getAllUsers();
        if (!CollectionUtils.isEmpty(users)) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        var newUser = userService.create(user);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/{id}/increment", produces = "application/json")
    public ResponseEntity<?> incrementOfPost(@PathVariable(value = "id") Long id) {
        if (userService.increaseAmountOfPosts(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/{id}/decrement", produces = "application/json")
    public ResponseEntity<?> decrementOfPost(@PathVariable(value = "id") Long id) {
        if (userService.decreaseAmountOfPosts(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        if (user.getUsername() == null) {
            return ResponseEntity.badRequest().body(new UserRequestFailure("Body is empty"));
        }
        var existUser = userService.update(id, user);
        if (existUser != null) {
            return new ResponseEntity<>(existUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        if (userService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
