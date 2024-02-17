package com.hack.cvcenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "user_experience")
public class UserExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;
    private String positionName;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String details;

    @JsonBackReference
    @ManyToOne
    private UserDetail userDetail;

}
