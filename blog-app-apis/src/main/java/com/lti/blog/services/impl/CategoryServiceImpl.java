package com.lti.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.blog.entities.Category;
import com.lti.blog.exceptions.ResourceNotFoundException;
import com.lti.blog.payloads.CategoryDto;
import com.lti.blog.repositories.CategoryRepo;
import com.lti.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto category) {
		Category cat=this.modelMapper.map(category, Category.class);
		Category addadCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addadCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		cat.setCategoryTitle(category.getCategoryTitle());
		cat.setCategoryDescription(category.getCategoryDescription());
		this.categoryRepo.save(cat);
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category>categories=this.categoryRepo.findAll();
		List<CategoryDto>categoriesDto=categories.stream().map(cat->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoriesDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		this.categoryRepo.delete(cat);
		
	}

}
