package com.lti.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lti.blog.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	
	private int id;
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name;
	
	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10,message = "Password must be min of 3 chars and max of 10 chars !!")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}
