package com.parishjain.InstagramApplication.service;


import com.parishjain.InstagramApplication.models.AuthenticationToken;
import com.parishjain.InstagramApplication.models.InstaUser;
import com.parishjain.InstagramApplication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepository authenticationRepository;

    public void saveToken(AuthenticationToken authenticationToken) {
        authenticationRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(InstaUser instaUser) {
        return authenticationRepository.findByInstaUser(instaUser);
    }

    public boolean authenticate(String token, String email){
        AuthenticationToken authToken = authenticationRepository.findFirstByToken(token);
        String expectedEmail = authToken.getInstaUser().getUserEmail();
        return expectedEmail.equals(email);
    }
}
