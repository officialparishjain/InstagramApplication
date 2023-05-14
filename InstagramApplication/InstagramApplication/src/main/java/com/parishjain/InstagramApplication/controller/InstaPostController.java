package com.parishjain.InstagramApplication.controller;


import com.parishjain.InstagramApplication.models.AuthenticationToken;
import com.parishjain.InstagramApplication.models.InstaPost;
import com.parishjain.InstagramApplication.service.AuthenticationService;
import com.parishjain.InstagramApplication.service.InstaPostService;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstaPostController {

    @Autowired
    InstaPostService instaPostService;

    @Autowired
    AuthenticationService authService;

    // CREATE THE POST
    @PostMapping("/posts")
    public ResponseEntity<InstaPost> createPost(@RequestBody InstaPost post) {
        InstaPost createdPost = instaPostService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    // GET ALL POSTS
    @GetMapping("/posts/all/{email}/{token}")
    ResponseEntity<List<InstaPost>> getAllPost(@PathVariable String email,@PathVariable String token){

        HttpStatus status;
        List<InstaPost> instaPostList = null;
        if(authService.authenticate(token,email)){
            instaPostList = instaPostService.fetchAllPost(email);
            status = HttpStatus.OK;
        }
        else{
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(instaPostList, status);
    }



}
