package com.bideeparts.gallery.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import com.bideeparts.gallery.persistence.entity.Category;
import com.bideeparts.gallery.service.CategoryService;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Category> getCategories() {
		return categoryService.getAllCategories();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Category create(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Category find(@PathVariable String id) {
		return categoryService.findById(id);
	}
	
	@DeleteMapping
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		categoryService.deleteCategory(id);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Category update(@PathVariable String id, @RequestBody Category category) {
		Category savedCategory = categoryService.updateCategory(category);
		if (savedCategory == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"); 
		}
		return savedCategory;
	}
	
}
