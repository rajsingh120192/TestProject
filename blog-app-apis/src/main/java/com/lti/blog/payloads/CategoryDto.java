package com.lti.blog.payloads;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categotyId;

	@NotBlank
	@Size(min = 4, max = 20, message = "CategoryTitle must be min of 4 chars and max of 20 chars !!")
	private String categoryTitle;

	@NotBlank
	@Size(min = 10, max = 100, message = "CategoryDescription must be min of 10 chars and max of 100 chars !!")
	private String categoryDescription;
}
