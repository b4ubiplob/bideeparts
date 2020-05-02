package com.bideeparts.gallery.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bideeparts.gallery.persistence.entity.Gallery;
import com.bideeparts.gallery.persistence.entity.Painting;
import com.bideeparts.gallery.persistence.repository.GalleryRepository;
import com.bideeparts.gallery.persistence.repository.PaintingRepository;

@Service
public class PaintingService {

	@Autowired
	private GalleryRepository galleryRepository;
	@Autowired
	private PaintingRepository paintingRepository;
	
	public Painting createPainting(Painting painting, String galleryId) {
		Gallery gallery = galleryRepository.getOne(galleryId);
		if (gallery == null) {
			throw new IllegalArgumentException("Could not find gallery with id : " + galleryId);
		}
		painting.setGallery(gallery);
		return paintingRepository.saveAndFlush(painting);
		
	}
	
	public Painting createPainting(MultipartFile file, Gallery gallery) {
		Painting painting = new Painting();
		painting.setGallery(gallery);
		Date date = new Date();
		painting.setCreatedOn(date);
		painting.setLastUpdatedOn(date);
		painting.setId(UniqueIdGenerator.getRandomId()	);
		try {
			painting.setContent(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paintingRepository.saveAndFlush(painting);
	}
	
	public List<Painting> getPaintingsOfGallery(String galleryId) {
		Gallery gallery = galleryRepository.getOne(galleryId);
		if (gallery == null) {
			throw new IllegalArgumentException("Could not find gallery with id : " + galleryId);
		}
		return paintingRepository.getPaintingsOfGallery(gallery);
	}
	
	public Painting getPainting(String id) {
		return paintingRepository.getOne(id);
	}
	
	public Painting updatePainting(Painting painting, String id) {
		Painting existingPainting = paintingRepository.getOne(id);
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("Painting not found with id :" + id);
		}
		if (Objects.nonNull(painting.getCategory())) {
			existingPainting.setCategory(painting.getCategory());
		}
		if (Objects.nonNull(painting.getDescription())) {
			existingPainting.setDescription(painting.getDescription());
		}
		if (Objects.nonNull(painting.getDimension())) {
			existingPainting.setDimension(painting.getDimension());
		}
		if (Objects.nonNull(painting.getGallery())) {
			existingPainting.setGallery(painting.getGallery());
		}
		if (Objects.nonNull(painting.getName())) {
			existingPainting.setName(painting.getName());
		}
		return paintingRepository.saveAndFlush(existingPainting);
	}
	
	public void deletePainting(String id) {
		paintingRepository.deleteById(id);
	}
	 
}
