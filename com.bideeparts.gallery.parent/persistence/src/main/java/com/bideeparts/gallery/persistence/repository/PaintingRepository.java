package com.bideeparts.gallery.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bideeparts.gallery.persistence.entity.Painting;

public interface PaintingRepository extends JpaRepository<Painting, String> {

}
