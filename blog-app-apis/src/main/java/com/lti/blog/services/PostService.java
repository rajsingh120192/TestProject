package com.lti.blog.services;

import java.util.List;

import com.lti.blog.entities.Post;
import com.lti.blog.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	Post updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDto> getAllPost();
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<Post> searchPosts(String keyword);
	
	
}
