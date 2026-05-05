package com.umfrancisco.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.umfrancisco.model.Post;
import com.umfrancisco.service.PostServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private final PostServiceImpl postService;
	
	public PostController(PostServiceImpl postService) {
		this.postService = postService;
	}
	
	@GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
	
	@PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        Post created = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
