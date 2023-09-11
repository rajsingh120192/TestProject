package com.lti.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.blog.entities.Category;
import com.lti.blog.entities.Post;
import com.lti.blog.entities.User;
import com.lti.blog.exceptions.ResourceNotFoundException;
import com.lti.blog.payloads.PostDto;
import com.lti.blog.payloads.UserDto;
import com.lti.blog.repositories.CategoryRepo;
import com.lti.blog.repositories.PostRepo;
import com.lti.blog.repositories.UserRepository;
import com.lti.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ", "UserId", userId));
        Category category= categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "CategoryId", categoryId));
		Post post=modelMapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
        Post newPost=postRepo.save(post);   
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
	Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "PostId", postId));
	postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post>posts=postRepo.findAll();
		List<PostDto>postDtos=posts.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "PostId", postId));
		PostDto postDto=modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category= categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "CategoryId", categoryId));
		List<Post>posts=postRepo.findByCategory(category);
		List<PostDto>postDtos=posts.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ", "UserId", userId));
		List<Post>posts=postRepo.findByUser(user);
		List<PostDto>postDtos=posts.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
