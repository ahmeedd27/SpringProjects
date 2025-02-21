package com.Ahmed.AhmedSpring.services;

import com.Ahmed.AhmedSpring.entity.JobSeekerProfile;
import com.Ahmed.AhmedSpring.entity.RecruiterProfile;
import com.Ahmed.AhmedSpring.entity.Users;
import com.Ahmed.AhmedSpring.entity.UsersType;
import com.Ahmed.AhmedSpring.repos.JobSeekerProfileRepo;
import com.Ahmed.AhmedSpring.repos.RecruiterProfileRepo;
import com.Ahmed.AhmedSpring.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepo ur;
    private final JobSeekerProfileRepo jspr;
    private final RecruiterProfileRepo rpr;
    private  PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();





    @Autowired
    public UsersService(UsersRepo ur , JobSeekerProfileRepo jspr ,
                        RecruiterProfileRepo rpr) {
        this.ur=ur;
        this.jspr=jspr;
        this.rpr=rpr;

    }

    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        Long userTypeId = users.getUserTypeId().getUserTypeId();
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users user=ur.save(users);
        if(userTypeId==1){
             rpr.save(new RecruiterProfile(user));
        }else{
            jspr.save(new JobSeekerProfile(user));
        }
        return user;
    }

    public Optional<Users> findByEmail(String email){
        return ur.findByEmail(email);
    }

    public Object getCurrentUserProfile() {

       Authentication principle= SecurityContextHolder.getContext().getAuthentication();
       if(!(principle instanceof AnonymousAuthenticationToken)){
           String username=principle.getName();
           Users user=ur.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found"));
           Long id=user.getUserId();
           if(principle.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
              RecruiterProfile recruiterProfile = rpr.findById(id)
                      .orElseThrow(() -> new UsernameNotFoundException("not found"));
             return recruiterProfile;
           }else{
               JobSeekerProfile jobSeekerProfile=jspr.findById(id)
                       .orElseThrow(() -> new UsernameNotFoundException("not found"));
               return jobSeekerProfile;
           }
       }
       return null;

    }

    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            Users user = ur.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not found " + "user"));
            return user;
        }

        return null;
    }
}
