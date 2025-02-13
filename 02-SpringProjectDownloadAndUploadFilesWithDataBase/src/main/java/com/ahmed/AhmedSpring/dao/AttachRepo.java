
package com.ahmed.AhmedSpring.dao;

import com.ahmed.AhmedSpring.entities.Attachment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachRepo extends JpaRepository<Attachment , Long> {
    
   Optional<Attachment> findByName(String fileName);
    
}
