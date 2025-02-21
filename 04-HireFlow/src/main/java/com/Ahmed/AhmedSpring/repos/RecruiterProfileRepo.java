package com.Ahmed.AhmedSpring.repos;

import com.Ahmed.AhmedSpring.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterProfileRepo extends JpaRepository<RecruiterProfile, Long> {
}
