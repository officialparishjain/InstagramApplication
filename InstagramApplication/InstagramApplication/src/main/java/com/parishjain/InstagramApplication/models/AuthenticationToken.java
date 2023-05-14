package com.parishjain.InstagramApplication.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private LocalDate tokenCreationDate;

    // AUTHENTICATION TOKEN -----------1-------------1-------------- InstaUser
    // Means that 1 token will be related to only one InstaUser
    @OneToOne
    @JoinColumn(nullable = false,name = "fk_instaUser_ID")
    private InstaUser instaUser;

    public AuthenticationToken(InstaUser instaUser) {
        this.instaUser = instaUser;
        this.token = UUID.randomUUID().toString();
        this.tokenCreationDate = LocalDate.now();
    }
}
