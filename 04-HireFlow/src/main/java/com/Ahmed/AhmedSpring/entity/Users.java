package com.Ahmed.AhmedSpring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Users {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    @Column(unique=true)
    private String email;

    @NotEmpty
    private String password;

    private boolean isActive;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date registrationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name="user_type_id" ,
            referencedColumnName = "userTypeId"
    )
    private UsersType userTypeId;



}
