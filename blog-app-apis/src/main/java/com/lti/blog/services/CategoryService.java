package com.lti.blog.services;

import java.util.List;

import com.lti.blog.payloads.CategoryDto;
import com.lti.blog.payloads.UserDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto category);
	CategoryDto updateCategory(CategoryDto category,Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategories();
	void deleteCategory(Integer categoryId);
}
