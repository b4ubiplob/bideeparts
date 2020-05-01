package com.bideeparts.gallery.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bideeparts.gallery.persistence.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
