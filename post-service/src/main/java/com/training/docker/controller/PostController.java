package com.training.docker.controller;

import com.training.docker.entity.Post;
import com.training.docker.service.PostService;
import com.training.docker.utils.PostRequestFailure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postServiceService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {
        var user = postServiceService.getPostById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllPosts() {
        var users = postServiceService.getAllPosts();
        if (!CollectionUtils.isEmpty(users)) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        var newPost = postServiceService.create(post);
        if (newPost != null) {
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> update(@RequestBody Post post, @PathVariable("id") Long id) {
        if (post.getAuthorId() == null && post.getText() == null ) {
            return ResponseEntity.badRequest().body(new PostRequestFailure("Body is empty"));
        }
        var existPost = postServiceService.update(id, post);
        if (existPost != null) {
            return new ResponseEntity<>(existPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (postServiceService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
