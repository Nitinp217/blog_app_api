package com.nitin.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4)
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10)
	private String categoryDesc;
}
