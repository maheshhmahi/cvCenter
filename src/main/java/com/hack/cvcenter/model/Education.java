package com.hack.cvcenter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    private String universityName;
    private String degree;
    private LocalDate startDate;
    private LocalDate endDate;
    private String gpa;

}
