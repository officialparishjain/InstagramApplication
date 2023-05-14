package com.parishjain.InstagramApplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InstaPost  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private Timestamp postCreatedDate;
    private Timestamp postUpdatedDate;
    private String postData;
    @ManyToOne(fetch = FetchType.LAZY)
    private InstaUser instaUser;
}
