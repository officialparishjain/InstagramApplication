package com.parishjain.InstagramApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class InstaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private Integer userAge;
    private String userEmail;
    private String userPhoneNumber;

    public InstaUser(String userFirstName, String userLastName, Integer userAge, String userEmail, String userPhoneNumber, String userPassword) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
    }

    private String userPassword;



}


