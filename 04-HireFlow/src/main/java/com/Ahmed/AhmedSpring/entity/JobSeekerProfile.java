package com.Ahmed.AhmedSpring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class JobSeekerProfile {

    @Id
     private Long userAccountId;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="user_id")
    @MapsId
    private Users userId;

    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String workAuthorization;
    private String employmentType;
    @Column(nullable = true , length = 64)
    private String profilePhoto;
    private String resume;

    @OneToMany(mappedBy = "jobSeekerProfile" , cascade= CascadeType.ALL)
    private List<Skills> skills;


    public JobSeekerProfile(Users users) {
        this.userId=users;
    }

    @Transient
    public String getPhotosImagePath() {
        if (profilePhoto == null || userAccountId == null) return null;
        return "photos/candidate/" + userAccountId + "/" + profilePhoto;
    }

    @Override
    public String toString() {
        return "JobSeekerProfile{" +
                "city='" + city + '\'' +
                ", userAccountId=" + userAccountId +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", workAuthorization='" + workAuthorization + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", resume='" + resume + '\'' +
                '}';
    }
}
