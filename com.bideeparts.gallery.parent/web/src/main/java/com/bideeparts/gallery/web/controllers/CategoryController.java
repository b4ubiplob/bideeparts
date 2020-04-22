package com.bideeparts.gallery.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bideeparts.gallery.persistence.entity.Category;
import com.bideeparts.gallery.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("api/v1/categories")
	public List<Category> getCategories() {
		return categoryService.getAllCategories();
	}

}
