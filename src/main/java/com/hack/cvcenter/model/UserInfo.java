package com.hack.cvcenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String phoneNumber;
    private String type;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    @JsonBackReference
    @OneToOne(mappedBy = "userInfo")
    private UserDetail userDetail;

}
