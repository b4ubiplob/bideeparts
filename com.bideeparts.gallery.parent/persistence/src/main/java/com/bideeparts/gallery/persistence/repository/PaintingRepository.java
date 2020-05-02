package com.bideeparts.gallery.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bideeparts.gallery.persistence.entity.Gallery;
import com.bideeparts.gallery.persistence.entity.Painting;

public interface PaintingRepository extends JpaRepository<Painting, String> {
	
	@Query("Select p from Painting p where p.gallery = ?1")
	public List<Painting> getPaintingsOfGallery(Gallery gallery);

}
