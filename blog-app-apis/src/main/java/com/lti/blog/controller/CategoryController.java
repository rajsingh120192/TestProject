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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.blog.payloads.ApiResponse;
import com.lti.blog.payloads.CategoryDto;
import com.lti.blog.payloads.UserDto;
import com.lti.blog.services.CategoryService;
import com.lti.blog.services.UserService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	
	@Autowired	
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createUser(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategoryDto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategoryDto, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>updateUser(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId)
	{
		CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updatedCategory);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?>deleteUser(@PathVariable Integer categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleUsers(@PathVariable Integer categoryId)
	{
		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}
}
