package com.hack.cvcenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "education")
public class Education implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    private String universityName;
    private String degree;
    private String major;
    private String startDate;
    private String endDate;
    private String gpa;

    @JsonBackReference
    @ManyToOne
    UserDetail userDetail;

}
