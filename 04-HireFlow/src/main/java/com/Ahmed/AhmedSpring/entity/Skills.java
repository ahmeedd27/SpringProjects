package com.Ahmed.AhmedSpring.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String experienceLevel;
    private String yearsOfExperience;

    @ManyToOne(cascade= CascadeType.ALL)
    private JobSeekerProfile jobSeekerProfile;
}
