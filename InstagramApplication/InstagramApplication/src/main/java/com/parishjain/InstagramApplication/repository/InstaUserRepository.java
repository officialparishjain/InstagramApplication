package com.parishjain.InstagramApplication.repository;

import com.parishjain.InstagramApplication.models.InstaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstaUserRepository extends JpaRepository<InstaUser,Integer>
{
    Boolean existsByUserEmail(String userEmail);

    InstaUser findFirstByUserEmail(String email);

    InstaUser findInstaUserByUserEmail(String email);

    @Query(value = "update insta_user set user_phone_number = :mobile where user_email = :email",nativeQuery = true)
    @Modifying
    void updateUserMobileByEmail(String email, String mobile);
}
