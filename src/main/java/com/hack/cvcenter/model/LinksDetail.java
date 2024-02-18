package com.hack.cvcenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "links")
public class LinksDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    private String resumeUrl;
    private String linkedInUrl;
    private String githubUrl;

    @JsonBackReference
    @OneToOne(mappedBy = "linksDetail")
    private UserDetail userDetail;
}
