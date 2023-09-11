package com.lti.blog.payloads;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.lti.blog.entities.Category;
import com.lti.blog.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private Integer postId;

	@NotBlank
	@Size(min = 4, max = 20, message = "Post Title must be min of 4 chars and max of 20 chars !!")
	private String title;

	@NotBlank
	@Size(min = 10, max = 200, message = "Post Content must be min of 10 chars and max of 100 chars !!")
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;

}
