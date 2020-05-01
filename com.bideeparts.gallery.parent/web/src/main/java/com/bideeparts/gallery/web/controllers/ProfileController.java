package com.bideeparts.gallery.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.bideeparts.gallery.persistence.entity.Profile;
import com.bideeparts.gallery.service.ProfileService;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
	
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping
	@RequestMapping("/{id}")
	public Profile getProfile(@PathVariable String id) {
		return profileService.getProfile(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Profile createProfile(@RequestBody Profile profile) {
		return profileService.createProfile(profile);
	}
	
	@PostMapping
	@RequestMapping("/{id}/photo")
	public void updateProfilePhoto(@PathVariable String id, @RequestParam("file") MultipartFile file) {
		Profile profile = profileService.getProfile(id);
		if (profile == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		profileService.updateProfilePhoto(profile, file);
	}
	
	
	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public Profile updateProfile(@PathVariable String id, @RequestBody Profile profile) {
		Profile savedProfile = profileService.updateProfile(profile);
		if (profile == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return savedProfile;
	}

}
