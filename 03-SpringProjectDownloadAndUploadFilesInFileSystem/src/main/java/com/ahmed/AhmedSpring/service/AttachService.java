package com.ahmed.AhmedSpring.service;

import com.ahmed.AhmedSpring.dao.AttachRepo;
import com.ahmed.AhmedSpring.entities.Attachment;
import com.ahmed.AhmedSpring.util.ImageUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachService {
    
    @Autowired
    private AttachRepo ar;
    private final String folderPath="E:\\ToUploadFiles"; 
    
    public String uploadFile(MultipartFile file) throws IOException {
        
        String filePath=folderPath+File.pathSeparator+file.getOriginalFilename();
        
        // i will store the file name and content type and path in the database
       Attachment att=ar.save( new Attachment(file.getOriginalFilename() 
       , file.getContentType() , filePath ));
       
       // now i want to store the file in the directory
       
       file.transferTo(new File(filePath));
       
       if(att!=null){
            return "file uploaded successfully "+file.getOriginalFilename();
          }
       return null;
        
    }
    
    public byte[] downloadFileFromFileSystem(String fileName) throws IOException{ // download i take form db ,,,, upload i ut in db
         Optional<Attachment> att=ar.findByName(fileName); // i get the file by the name
        String filePath=att.get().getFilePath(); // i get the filepath 
        byte[] dow=Files.readAllBytes(new File(filePath).toPath()); // i read the path of the file from my directory and convert it to byte array
        return dow; // i return the file
    }
    
}
