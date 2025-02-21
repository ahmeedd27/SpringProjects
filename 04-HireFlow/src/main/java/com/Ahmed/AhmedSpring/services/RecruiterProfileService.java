package com.Ahmed.AhmedSpring.services;

import com.Ahmed.AhmedSpring.entity.RecruiterProfile;
import com.Ahmed.AhmedSpring.repos.RecruiterProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepo rpr;
    @Autowired
    public RecruiterProfileService(RecruiterProfileRepo rpr){
        this.rpr=rpr;
    }

    public Optional<RecruiterProfile> getOne(Long id){
       return  rpr.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return rpr.save(recruiterProfile);
    }
}
