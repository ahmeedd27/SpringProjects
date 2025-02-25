package com.Ahmed.AhmedSpring.repo;

import com.Ahmed.AhmedSpring.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepo extends MongoRepository<Post,String> {
    
}
