package com.umfrancisco.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.umfrancisco.model.Post;
import com.umfrancisco.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findById(long id);
	Optional<Post> findByTitle(String title);
	Optional<Post> findByUser(User user);
	boolean existsByTitle(String title);
}
