package com.parishjain.InstagramApplication.repository;

import com.parishjain.InstagramApplication.models.AuthenticationToken;
import com.parishjain.InstagramApplication.models.InstaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByInstaUser(InstaUser instaUser);

    AuthenticationToken findFirstByToken(String token);
}
