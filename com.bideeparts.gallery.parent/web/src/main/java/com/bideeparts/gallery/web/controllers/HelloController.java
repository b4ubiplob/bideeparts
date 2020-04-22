package com.bideeparts.gallery.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/")
	public String index() {
		return "Hello world from gallery";
	}
	

}
