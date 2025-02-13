/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ahmed.AhmedSpring.controllers;

import com.ahmed.AhmedSpring.service.AttachService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class AttachController {
    
    @Autowired
   private AttachService as;
    
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException
    {
        String up=as.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(up);
    }    
    
    @GetMapping("/download/{fileName}")
     public ResponseEntity<?> downloadFile(@PathVariable("fileName") String fileName)
    {
        byte[] dow=as.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(dow);
    }   
}
