package com.umfrancisco.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.umfrancisco.exception.DuplicateResourceException;
import com.umfrancisco.model.Post;
import com.umfrancisco.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	private final PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(long id) {
		return null;
	}

	@Override
	public Post createPost(Post post) {
		if (postRepository.existsByTitle(post.getTitle())) {
			throw new DuplicateResourceException("Title already taken: "+post.getTitle());
		}
		Post saved = postRepository.save(post);
		return saved;
	}

	@Override
	public Post updatePost(long id, Post post) {
		return null;
	}

	@Override
	public void deletePost(long id) {
		
	}
}
