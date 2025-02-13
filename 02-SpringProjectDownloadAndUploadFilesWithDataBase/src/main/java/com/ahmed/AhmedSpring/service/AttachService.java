package com.ahmed.AhmedSpring.service;

import com.ahmed.AhmedSpring.dao.AttachRepo;
import com.ahmed.AhmedSpring.entities.Attachment;
import com.ahmed.AhmedSpring.util.ImageUtils;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachService {
    
    @Autowired
    private AttachRepo ar;
    
    public String uploadFile(MultipartFile file) throws IOException {
       Attachment att= ar.save(new Attachment(file.getOriginalFilename() 
               , file.getContentType() , ImageUtils.compressImage(file.getBytes()) ));
        
       if(att!=null){
           return "file uploaded successfully "+file.getOriginalFilename();
       }
       return null;
        
    }
    
    public byte[] downloadFile(String fileName){ // download i take form db ,,,, upload i ut in db
         Optional<Attachment> att=ar.findByName(fileName);
         return ImageUtils.decompressImage(att.get().getImageData()); // get() ->beacuse it is optional
    }
    
}
