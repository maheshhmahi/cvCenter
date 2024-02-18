package com.hack.cvcenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "links")
public class LinksDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    private String resumeUrl;
    private String linkedInUrl;
    private String githubUrl;

    @OneToOne(mappedBy = "linksDetail")
    @JsonIgnoreProperties("links")
    private UserDetail userDetail;
}
