package com.Ahmed.AhmedSpring.controller;

import com.Ahmed.AhmedSpring.entity.RecruiterProfile;
import com.Ahmed.AhmedSpring.entity.Users;
import com.Ahmed.AhmedSpring.services.RecruiterProfileService;
import com.Ahmed.AhmedSpring.services.UsersService;
import com.Ahmed.AhmedSpring.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {
    private final UsersService us;
    private final RecruiterProfileService rps ;
    @Autowired
    public RecruiterProfileController(UsersService us , RecruiterProfileService rps ){
        this.us=us;
        this.rps=rps;
    }

    @GetMapping("/")
    public String recruiterProfile(Model m){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     if(!(authentication instanceof AnonymousAuthenticationToken)){
         String name=authentication.getName();
        Users user= us.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("Not Found"));

        Optional<RecruiterProfile> rrecruiterProfile=rps.getOne(user.getUserId());
        if(rrecruiterProfile.isPresent()){
            m.addAttribute("profile" , rrecruiterProfile.get());

        }

     }
       return "recruiter_profile";
     }

     @PostMapping("/addNew")
     public String addNew(RecruiterProfile recruiterProfile , @RequestParam("multipartFile") MultipartFile
                          multipartFile , Model m){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String name=authentication.getName();
            Users user= us.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("Not Found"));
            recruiterProfile.setUserId(user);
            recruiterProfile.setUserAccountId(user.getUserId());
        }
        m.addAttribute("profile" , recruiterProfile);
        String filename="";
        if(!multipartFile.getOriginalFilename().equals("")){
            filename= StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            recruiterProfile.setProfilePhoto(filename);
        }
        RecruiterProfile saved=rps.addNew(recruiterProfile);
         String uploadDir = "photos/recruiter/" + saved.getUserAccountId();

         try{
            FileUploadUtil.saveFile(uploadDir , filename , multipartFile);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/dashboard/";
     }

}
