package com.parishjain.InstagramApplication.repository;

import com.parishjain.InstagramApplication.models.InstaPost;
import com.parishjain.InstagramApplication.models.InstaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstaPostRepository extends JpaRepository<InstaPost,Integer> {

    List<InstaPost> findByInstaUser(InstaUser instaUser);
}