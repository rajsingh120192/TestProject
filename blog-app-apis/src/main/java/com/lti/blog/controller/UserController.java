package com.lti.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.blog.payloads.ApiResponse;
import com.lti.blog.payloads.UserDto;
import com.lti.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

@Autowired	
private UserService userService;

@PostMapping("/")
public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto)
{
	UserDto createUserDto=this.userService.createUser(userDto);
	return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
}

@PutMapping("/{userId}")
public ResponseEntity<UserDto>updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId)
{
	UserDto updatedUser=this.userService.updateUser(userDto, userId);
	return ResponseEntity.ok(updatedUser);
}
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{userId}")
public ResponseEntity<?>deleteUser(@PathVariable Integer userId)
{
	this.userService.deleteUser(userId);
	return new ResponseEntity(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
}

@GetMapping("/")
public ResponseEntity<List<UserDto>> getAllUsers()
{
	return ResponseEntity.ok(this.userService.getAllUsers());
}

@GetMapping("/{userId}")
public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId)
{
	return ResponseEntity.ok(this.userService.getUserById(userId));
}
}
