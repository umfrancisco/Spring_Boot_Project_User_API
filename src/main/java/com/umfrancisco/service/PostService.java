package com.umfrancisco.service;

import java.util.List;
import com.umfrancisco.model.Post;

public interface PostService {
	List<Post> getAllPosts();
	Post getPostById(long id);
	Post createPost(Post post);
	Post updatePost(long id, Post post);
	void deletePost(long id);
}
