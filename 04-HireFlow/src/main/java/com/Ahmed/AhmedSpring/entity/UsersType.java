package com.Ahmed.AhmedSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UsersType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userTypeId;

    private String userTypeName;

    @OneToMany(mappedBy="userTypeId" , cascade = CascadeType.ALL)
    private List<Users> users;

    @Override
    public String toString() {
        return "UsersType{" +
                "userTypeId=" + userTypeId +
                ", userTypeName='" + userTypeName + '\'' +
                '}';
    }
}
