package com.bideeparts.gallery.service;

import java.util.UUID;

public class UniqueIdGenerator {
	
	public static String getRandomId() {
		return UUID.randomUUID().toString();
	}

}
