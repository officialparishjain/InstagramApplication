package com.parishjain.InstagramApplication.controller;

import com.parishjain.InstagramApplication.dto.SignInInput;
import com.parishjain.InstagramApplication.dto.SignInOutput;
import com.parishjain.InstagramApplication.dto.SignUpInput;
import com.parishjain.InstagramApplication.dto.SignUpOutput;
import com.parishjain.InstagramApplication.service.AuthenticationService;
import com.parishjain.InstagramApplication.service.InstaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InstaUserController {

    @Autowired
    InstaUserService instaUserService;

    @Autowired
    AuthenticationService authenticationService;

    // INSTA USER SIGN UP
    @PostMapping("/signup")
    public ResponseEntity<SignUpOutput> signup(@RequestBody SignUpInput signUpInput)  {
        return instaUserService.signUpUser(signUpInput);
    }

    // INSTA USER SIGN IN
    @PostMapping("/signin")
    public ResponseEntity<SignInOutput> signin(@RequestBody SignInInput signInInput){
        return instaUserService.signInUser(signInInput);
    }

    // UPDATE USER DETAIL
    @PutMapping("/update/mobile/{mobile}/{email}/{token}")
    ResponseEntity<String> updateUserMobileByEmail(@PathVariable String email,
                                                   @PathVariable String token,
                                                   @PathVariable String mobile){

        if(authenticationService.authenticate(token,email)){
            instaUserService.updateUserMobileByEmail(email,mobile);
            return ResponseEntity.ok().body("Updated...");
        }
        else{
            return ResponseEntity.badRequest().body("Not Updated... Check Token...");
        }
    }
}
