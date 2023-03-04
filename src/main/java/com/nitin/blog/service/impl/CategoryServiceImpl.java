package com.nitin.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitin.blog.entity.Category;
import com.nitin.blog.exception.ResourceNotFoundException;
import com.nitin.blog.payloads.CategoryDto;
import com.nitin.blog.repository.ICategoryRepo;
import com.nitin.blog.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepo catRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		Category category = mapper.map(dto, Category.class);
		Category savedCategory = catRepo.save(category);
		return mapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto dto, Integer categoryId) {
		CategoryDto categoryDto = this.getOneCategoryById(categoryId);
		Category category = mapper.map(categoryDto, Category.class);
		category.setCategoryTitle(dto.getCategoryTitle());
		category.setCategoryDesc(dto.getCategoryDesc());
		return mapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		CategoryDto oneCategoryById = this.getOneCategoryById(categoryId);
		catRepo.delete(mapper.map(oneCategoryById, Category.class));
	}

	@Override
	public CategoryDto getOneCategoryById(Integer categoryId) {
		Category category = catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		return mapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = catRepo.findAll();
		System.out.println(categories);
		List<CategoryDto> categoryDtos = categories.stream().map((cat) -> mapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		System.out.println(categoryDtos);
		return categoryDtos;
	}

}
