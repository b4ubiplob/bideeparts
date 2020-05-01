package com.bideeparts.gallery.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bideeparts.gallery.persistence.entity.Category;
import com.bideeparts.gallery.persistence.entity.Gallery;
import com.bideeparts.gallery.persistence.repository.GalleryRepository;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryRepository galleryRepository;
	
	public List<Gallery> getAllGalleries() {
		return galleryRepository.findAll();
	}
	
	public Gallery createGallery(Gallery gallery) {
		gallery.setId(UniqueIdGenerator.getRandomId());
		Date date = new Date();
		gallery.setCreatedOn(date);
		gallery.setLastUpdatedOn(date);
		return galleryRepository.saveAndFlush(gallery);
	}
	
	public Gallery findById(String id) {
		return galleryRepository.getOne(id);
	}
	
	public void deleteGallery(String id) {
		galleryRepository.deleteById(id);
	}
	
	public Gallery updateGallery(Gallery gallery) {
		Gallery existingGallery = galleryRepository.getOne(gallery.getId());
		if (existingGallery == null) {
			return null;
		}
		if (Objects.nonNull(gallery.getDescription())) {
			existingGallery.setDescription(gallery.getDescription());
		}
		if (Objects.nonNull(gallery.getName())) {
			existingGallery.setName(gallery.getName());
		}
		existingGallery.setLastUpdatedOn(new Date());
		return galleryRepository.saveAndFlush(existingGallery);
	}
	

}
