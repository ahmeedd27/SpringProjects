package com.Ahmed.AhmedSpring.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class RecruiterProfile {

    @Id
    private Long userAccountId;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    @MapsId
    private Users userId;

    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    @Column(nullable = true , length = 64)
    private String profilePhoto;
    private String company;

    @Transient
    public String getPhotosImagePath(){

            if (profilePhoto == null) return null;
            return "/photos/recruiter/" + userAccountId + "/" + profilePhoto;


    }


    public RecruiterProfile(Users users) {
        this.userId=users;
    }

}
