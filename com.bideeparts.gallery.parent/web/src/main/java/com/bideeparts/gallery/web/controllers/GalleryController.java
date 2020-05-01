package com.bideeparts.gallery.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.bideeparts.gallery.persistence.entity.Category;
import com.bideeparts.gallery.persistence.entity.Gallery;
import com.bideeparts.gallery.service.CategoryService;
import com.bideeparts.gallery.service.GalleryService;

@RestController
@RequestMapping("api/v1/galleries")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@GetMapping
	public List<Gallery> getGalleries() {
		return galleryService.getAllGalleries();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Gallery create(@RequestBody Gallery gallery) {
		return galleryService.createGallery(gallery);
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Gallery find(@PathVariable String id) {
		return galleryService.findById(id);
	}
	
	@DeleteMapping
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		galleryService.deleteGallery(id);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Gallery update(@PathVariable String id, @RequestBody Gallery gallery) {
		Gallery savedGallery = galleryService.updateGallery(gallery);
		if (savedGallery == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gallery not found"); 
		}
		return savedGallery;
	}

}
