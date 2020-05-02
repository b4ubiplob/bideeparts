package com.bideeparts.gallery.web.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.bideeparts.gallery.persistence.entity.Gallery;
import com.bideeparts.gallery.persistence.entity.Painting;
import com.bideeparts.gallery.service.GalleryService;
import com.bideeparts.gallery.service.PaintingService;

@RestController
@RequestMapping("api/v1/galleries/{galleryId}/paintings")
public class PaintingController {
	
	@Autowired
	private PaintingService paintingService;
	@Autowired 
	private GalleryService galleryService;
	
	@GetMapping
	public List<Painting> getPaintings(@PathVariable String galleryId) {
		return paintingService.getPaintingsOfGallery(galleryId);
	}

	@PostMapping
	@RequestMapping("/upload")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Painting> createPaintings(@PathVariable String galleryId, @RequestParam("files") MultipartFile[] files) {
		Gallery gallery = galleryService.findById(galleryId);
		if (gallery == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gallery with id " + galleryId + " not found");
		}
		return Arrays.asList(files)
				.stream()
				.map((file) -> paintingService.createPainting(file, gallery))
				.collect(Collectors.toList());
		
//		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
//                file.getContentType(), file.getSize());
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Painting updatePainting(@PathVariable String galleryId, @PathVariable String id, @RequestBody Painting painting) {
		Gallery gallery = galleryService.findById(galleryId);
		if (gallery == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gallery with id " + galleryId + " not found");
		}
		return paintingService.updatePainting(painting, id);
	}
	
	@RequestMapping(value = "/{id}/content", method = RequestMethod.GET)
	public ResponseEntity<Resource> getPaintingContent(@PathVariable String galleryId, @PathVariable String id) {
		Gallery gallery = galleryService.findById(galleryId);
		if (gallery == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gallery with id " + galleryId + " not found");
		}
		Painting painting =  paintingService.getPainting(id);
		if (painting == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Painting with id " + id + " not found");
		}
		return ResponseEntity.ok().body(new ByteArrayResource(painting.getContent()));
		
//		return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
//                .body(new ByteArrayResource(dbFile.getData()));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Painting getPainting(@PathVariable String galleryId, @PathVariable String id) {
		Gallery gallery = galleryService.findById(galleryId);
		if (gallery == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gallery with id " + galleryId + " not found");
		}
		Painting painting =  paintingService.getPainting(id);
		if (painting == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Painting with id " + id + " not found");
		}
		return painting;
	}
}
