package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employee.service.impl.Imageserviceimpl;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	  @Autowired
	    private Imageserviceimpl imageServiceimpl;

	    @PostMapping("/upload")
	    public ResponseEntity<String> uploadImage(
	            @RequestParam("file") MultipartFile file) {

	        return ResponseEntity.ok(
	        		imageServiceimpl.uploadImage(file));
	    
	    }
}
