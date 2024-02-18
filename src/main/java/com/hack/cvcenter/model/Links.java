package com.hack.cvcenter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "links")
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    private String resumeUrl;
    private String linkedInUrl;
    private String githubUrl;

    @OneToOne(mappedBy = "links")
    private UserDetail userDetail;
}
