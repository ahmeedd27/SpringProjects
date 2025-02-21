package com.Ahmed.AhmedSpring.services;


import com.Ahmed.AhmedSpring.entity.JobSeekerProfile;
import com.Ahmed.AhmedSpring.repos.JobSeekerProfileRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepo jobSeekerProfileRepository;

    public JobSeekerProfileService(JobSeekerProfileRepo jobSeekerProfileRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }

    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(Long.valueOf(id));
    }

    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }
}
