package com.employee.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.entity.Image;
import com.employee.repo.Imagerepo;

@Service
public class Imageserviceimpl {

	@Autowired
    private Imagerepo imageRepo;

    public String uploadImage(MultipartFile file) {

        try {

            String uploadDir = "uploads/";

            File directory = new File(uploadDir);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName =
                    System.currentTimeMillis() +
                    "_" +
                    file.getOriginalFilename();

            Path path = Paths.get(uploadDir + fileName);

            Files.copy(
                    file.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING);

            Image image = new Image();

            image.setFileName(fileName);
            image.setFilePath(path.toString());

            imageRepo.save(image);

            return "Image Uploaded Successfully";

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    
}
}