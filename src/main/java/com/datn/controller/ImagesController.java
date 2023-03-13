package com.datn.controller;

import com.datn.serviceimpl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploads")
public class ImagesController {
    @Autowired
    private HotelService service;
    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> readFile(@PathVariable String fileName){
        try {
            byte[] bytes = service.readContentFile(fileName);
            return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
}
