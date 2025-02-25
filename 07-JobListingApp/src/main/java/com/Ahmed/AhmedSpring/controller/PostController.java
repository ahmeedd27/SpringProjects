package com.Ahmed.AhmedSpring.controller;


import com.Ahmed.AhmedSpring.repo.PostRepo;
import com.Ahmed.AhmedSpring.model.Post;
import com.Ahmed.AhmedSpring.service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "JobListing", description = "Endpoints JobListing")
public class PostController {
    @Autowired
    private  PostRepo pr;

    @Autowired
    private service s;

    @GetMapping("/posts")
    @Operation(summary = "Get all posts", description = "Retrieve a list of all posts")
    public List<Post> getAllPosts(){
        return pr.findAll();
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
        return pr.save(post);
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text){
        return s.findByText(text);
    }








}
