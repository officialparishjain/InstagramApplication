package com.parishjain.InstagramApplication.service;

import com.parishjain.InstagramApplication.dto.SignInInput;
import com.parishjain.InstagramApplication.dto.SignInOutput;
import com.parishjain.InstagramApplication.dto.SignUpInput;
import com.parishjain.InstagramApplication.dto.SignUpOutput;
import com.parishjain.InstagramApplication.models.AuthenticationToken;
import com.parishjain.InstagramApplication.models.InstaUser;
import com.parishjain.InstagramApplication.repository.InstaUserRepository;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class InstaUserService {

    @Autowired
    InstaUserRepository instaUserRepository;

    @Autowired
    AuthenticationService authenticationService;

    public ResponseEntity<SignUpOutput> signUpUser(SignUpInput signUpInput){


        // First we will Check user exist or not by email
        Boolean isPresent = instaUserRepository.existsByUserEmail(signUpInput.getUserEmail());

        if(isPresent){
            // USER IS ALREADY PRESENT
            return ResponseEntity.badRequest().body(new SignUpOutput("failure", "User already exists."));
        }
        else{

            // USER IS NOT PRESENT SO WE WILL REGISTER HIM

            // We will First Encrypt the password of user
            String encryptPassword = null;
            try{
                encryptPassword = generateEncryptedPassword(signUpInput.getUserPassword());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

            InstaUser instaUser = new InstaUser(signUpInput.getUserFirstName(),
                                                signUpInput.getUserLastName(),
                                                signUpInput.getUserAge(),
                                                signUpInput.getUserEmail(),
                                                signUpInput.getUserPhoneNumber(),
                                                encryptPassword);

            instaUserRepository.save(instaUser);

            // Generate token
            AuthenticationToken authenticationToken = new AuthenticationToken(instaUser);
            authenticationService.saveToken(authenticationToken);
            return ResponseEntity.ok().body(new SignUpOutput("success", "User created successfully."));
        }
    }

    private String generateEncryptedPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        // Get the user's password as a byte array and update the message digest with it
        messageDigest.update(userPassword.getBytes());
        // Calculate the MD5 hash of the password
        byte[] digested = messageDigest.digest();
        // Concert Byte Array to hexadecimal String
        return DatatypeConverter.printHexBinary(digested);
    }

    public ResponseEntity<SignInOutput> signInUser(SignInInput signInInput) {


        InstaUser instaUser = instaUserRepository.findFirstByUserEmail(signInInput.getUserEmail());
        if(instaUser == null ){
            return ResponseEntity.badRequest().body(new SignInOutput("failure", "User does not exists."));
        }

        // Now we will encrypt  password
        String encryptedPassword = null;
        try{
            encryptedPassword = generateEncryptedPassword(signInInput.getPassword());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }



        // MATCH IT WITH DATABASE ENCRYPTED PASSWORD
        boolean isValidUser = encryptedPassword.equals(instaUser.getUserPassword());

        if(!isValidUser){
            return ResponseEntity.badRequest().body(new SignInOutput("failure", "User password does not match."));
        }

        AuthenticationToken authenticationToken = authenticationService.getToken(instaUser);
        String token = authenticationToken.getToken();
        return ResponseEntity.ok().body(new SignInOutput( token,"Authentication Success.."));

    }

    public InstaUser getInstaUserById(Integer userId) {
        Optional<InstaUser> optionalInstaUser =  instaUserRepository.findById(userId);
        if(optionalInstaUser.isPresent()) {
            InstaUser instaUser = optionalInstaUser.get();
            return instaUser;
        }
        else return null;
    }



    public InstaUser findInstaUserByUserEmail(String email) {
        return  instaUserRepository.findInstaUserByUserEmail(email);
    }

    @Transactional
    public void updateUserMobileByEmail(String email, String mobile) {
        instaUserRepository.updateUserMobileByEmail(email,mobile);
    }
}
