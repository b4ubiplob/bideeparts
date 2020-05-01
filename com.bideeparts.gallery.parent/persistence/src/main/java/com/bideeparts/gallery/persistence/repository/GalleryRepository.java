package com.bideeparts.gallery.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bideeparts.gallery.persistence.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, String> {

}
