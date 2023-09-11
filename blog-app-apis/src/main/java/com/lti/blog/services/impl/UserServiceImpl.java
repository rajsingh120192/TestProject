package com.lti.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.blog.entities.User;
import com.lti.blog.exceptions.ResourceNotFoundException;
import com.lti.blog.payloads.UserDto;
import com.lti.blog.repositories.UserRepository;
import com.lti.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateUser=this.userRepo.save(user);
		UserDto userDtoOne=this.userToDto(updateUser);
		return userDtoOne; 
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto>userDto=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		this.userRepo.delete(user);
	}

	public User dtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto, User.class);
		return user; 
	}
	
	public UserDto userToDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
}
