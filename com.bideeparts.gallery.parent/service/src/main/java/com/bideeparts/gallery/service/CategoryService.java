package com.bideeparts.gallery.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bideeparts.gallery.persistence.entity.Category;
import com.bideeparts.gallery.persistence.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	public Category createCategory(Category category) {
		category.setId(UniqueIdGenerator.getRandomId());
		Date date = new Date();
		category.setCreatedOn(date);
		category.setLastUpdatedOn(date);
		return categoryRepository.saveAndFlush(category);
	}
	
	public Category findById(String id) {
		return categoryRepository.getOne(id);
	}
	
	public void deleteCategory(String id) {
		categoryRepository.deleteById(id);
	}
	
	public Category updateCategory(Category category) {
		Category existingCategory = categoryRepository.getOne(category.getId());
		if (existingCategory == null) {
			return null;
		}
		existingCategory.setDescription(category.getDescription());
		existingCategory.setName(category.getName());
		existingCategory.setLastUpdatedOn(new Date());
		return categoryRepository.saveAndFlush(existingCategory);
	}

}
