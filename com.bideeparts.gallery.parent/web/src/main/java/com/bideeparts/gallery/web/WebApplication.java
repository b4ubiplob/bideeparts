package com.bideeparts.gallery.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.bideeparts.gallery")
@EntityScan(basePackages = {"com.bideeparts.gallery"})
@EnableJpaRepositories(basePackages = {"com.bideeparts.gallery"})
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
