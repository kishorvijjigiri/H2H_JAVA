package com.h2h.boot.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
//    @Column(unique = true)
    private String email;
    private String mobileNo;
    private Long aadhaarNo;
    private String bloodGroup;
    private String country;
    private String state;
    private String district;
    private String cityOrVillage;
    private Long pinCode;
    private String password;
    private String conformPassword;
}
