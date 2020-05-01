package com.bideeparts.gallery.service;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bideeparts.gallery.persistence.entity.Profile;
import com.bideeparts.gallery.persistence.repository.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	
	public Profile getProfile(String id) {
		return profileRepository.getOne(id);
	}
	
	public Profile createProfile(Profile profile) {
		Date date = new Date();
		profile.setCreatedOn(date);
		profile.setLastUpdatedOn(date);
		profile.setId(UniqueIdGenerator.getRandomId());
		return profileRepository.saveAndFlush(profile);
	}

	public Profile updateProfile(Profile profile) {
		Profile existingProfile =  profileRepository.getOne(profile.getId());
		
		if (Objects.isNull(existingProfile)) {
			return null;
		}
		
		if (Objects.nonNull(profile.getAbout())) {
			existingProfile.setAbout(profile.getAbout());
		}
		if (Objects.nonNull(profile.getEmail())) {
			existingProfile.setEmail(profile.getEmail());
		}
		if (Objects.nonNull(profile.getFacebookId())) {
			existingProfile.setFacebookId(profile.getFacebookId());
		}
		if (Objects.nonNull(profile.getInstagramId())) {
			existingProfile.setInstagramId(profile.getInstagramId());
		}
		if (Objects.nonNull(profile.getName())) {
			existingProfile.setName(profile.getName());
		}
		if (Objects.nonNull(profile.getPicture())) {
			existingProfile.setPicture(profile.getPicture());
		}
		return profileRepository.saveAndFlush(existingProfile);
 	}
	
	public void updateProfilePhoto(Profile profile, MultipartFile file) {
		try {
			profile.setPicture(file.getBytes());
			profileRepository.saveAndFlush(profile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
