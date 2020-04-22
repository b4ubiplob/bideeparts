package com.bideeparts.gallery.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bideeparts.gallery.persistence.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
