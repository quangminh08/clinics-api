package vn.dev.clinics.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import vn.dev.clinics.service.CloudinaryService;

@RestController
@RequestMapping("/cloudinary/upload")
@RequiredArgsConstructor
public class CloudinaryImageUploadController {

	@Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<Map> uploadImage(@RequestParam("image")MultipartFile file){
        Map data = this.cloudinaryService.upload(file);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
