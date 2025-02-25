package com.Ahmed.AhmedSpring;

import com.Ahmed.AhmedSpring.model.Post;
import com.Ahmed.AhmedSpring.repo.PostRepo;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import com.mongodb.client.AggregateIterable;

@Service
public class service {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoConverter converter;

      public List<Post> findByText(String text){

       List<Post> posts=new ArrayList<>();
       MongoDatabase database = mongoClient.getDatabase("ahmed");
       MongoCollection<Document> collection = database.getCollection("JobPost");

       AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                       new Document("text",
                               new Document("query", text)
                                       .append("path", Arrays.asList("desc", "profile", "techs")))),
               new Document("$sort",
                       new Document("exp", 1L))));

       result.forEach(doc -> posts.add(converter.read(Post.class , doc)));

       return posts;
      }
}
