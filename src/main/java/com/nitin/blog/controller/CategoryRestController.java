package com.nitin.blog.controller;

import java.util.List;

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

import com.nitin.blog.payloads.ApiResponse;
import com.nitin.blog.payloads.CategoryDto;
import com.nitin.blog.service.ICategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

	@Autowired
	private ICategoryService service;

	// create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto catDto) {
		CategoryDto categoryDto = service.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto dto, @PathVariable Integer categoryId) {
		CategoryDto updateCategory = service.updateCategory(dto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		service.deleteCategory(categoryId);
		return ResponseEntity.ok(new ApiResponse("Category deleted successfully ..!!", true));
	}

	// get one
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> fetchOneCategory(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(service.getOneCategoryById(categoryId));
	}

	// get all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> fetchAllCategories() {
		return ResponseEntity.ok(service.getAllCategories()); 
	}
}
