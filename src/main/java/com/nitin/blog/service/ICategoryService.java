package com.nitin.blog.service;

import java.util.List;

import com.nitin.blog.payloads.CategoryDto;

public interface ICategoryService {

	// create
	CategoryDto createCategory(CategoryDto dto);

	//update
	CategoryDto updateCategory(CategoryDto dto, Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//get one
	CategoryDto getOneCategoryById(Integer categoryId);
	
	//get all
	List<CategoryDto> getAllCategories();
}
