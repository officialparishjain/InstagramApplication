package com.parishjain.InstagramApplication.service;


import com.parishjain.InstagramApplication.dto.SignUpInput;
import com.parishjain.InstagramApplication.dto.SignUpOutput;
import com.parishjain.InstagramApplication.models.InstaPost;
import com.parishjain.InstagramApplication.models.InstaUser;
import com.parishjain.InstagramApplication.repository.InstaPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class InstaPostService  {

    @Autowired
    InstaPostRepository instaPostRepository;

    @Autowired
    InstaUserService instaUserService;


    public InstaPost createPost(InstaPost instaPost) {

        Integer userId = instaPost.getInstaUser().getUserId();
        InstaUser instaUser = instaUserService.getInstaUserById(userId);

        instaPost.setInstaUser(instaUser);

        // Set the post creation date and post update date to current time stamp;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        instaPost.setPostCreatedDate(now);
        instaPost.setPostUpdatedDate(now);

        return instaPostRepository.save(instaPost);
    }

    public List<InstaPost> fetchAllPost(String email) {
        InstaUser instaUser = instaUserService.findInstaUserByUserEmail(email);

        return instaPostRepository.findByInstaUser(instaUser);
    }
}
