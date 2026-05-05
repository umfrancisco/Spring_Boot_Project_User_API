package com.umfrancisco.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postId;
	@NotBlank(message = "Title is required")
	@Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    @Column(nullable = false, length = 50)
	private String title;
	@NotBlank(message = "Text is required")
	@Size(min = 5, max = 150, message = "Text must be between 5 and 150 characters")
    @Column(nullable = false, length = 50)
	private String text;
	@ManyToOne
	private User user;
	
	public Post() {
		
	}
	
	public Post(long postId,
			@NotBlank(message = "Title is required") @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters") String title,
			@NotBlank(message = "Text is required") @Size(min = 5, max = 150, message = "Text must be between 5 and 150 characters") String text,
			User user) {
		this.postId = postId;
		this.title = title;
		this.text = text;
		this.user = user;
	}
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", text=" + text + ", user=" + user + "]";
	}
}
