package com.lti.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.blog.entities.Post;
import com.lti.blog.payloads.ApiResponse;
import com.lti.blog.payloads.PostDto;
import com.lti.blog.payloads.UserDto;
import com.lti.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

@Autowired
PostService postService;
	
@PostMapping("/user/{userId}/category/{categoryId}/posts")	
public ResponseEntity<PostDto> createPost( @Valid @RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId)
{
	PostDto createdPost=postService.createPost(postDto, userId, categoryId);
	return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
}

@GetMapping("category/{categoryId}/posts")
public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId)
{
	return ResponseEntity.ok(postService.getPostByCategory(categoryId));
}

@GetMapping("user/{userId}/posts")
public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId)
{
	return ResponseEntity.ok(postService.getPostByUser(userId));
}

@DeleteMapping("/{postId}/posts")
public ResponseEntity<?>deletePost(@PathVariable Integer postId)
{
	this.postService.deletePost(postId);
	return new ResponseEntity(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
}

@GetMapping("/posts")
public ResponseEntity<List<PostDto>> getAllPosts()
{
	return ResponseEntity.ok(postService.getAllPost());
}

@GetMapping("post/{postId}/posts")
public ResponseEntity<PostDto> getByPostId(@PathVariable Integer postId)
{
	return ResponseEntity.ok(postService.getPostById(postId));
}
}