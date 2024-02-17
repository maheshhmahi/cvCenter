package com.hack.cvcenter.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "user_detail")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String email;
    private String password;
    private String dob;
    private String firstName;
    private String lastName;
    private Boolean isRecruiter;

    @JsonManagedReference
    @OneToMany
    private List<UserExperience> userExperience;

    @JsonManagedReference
    @OneToMany
    private List<Education> userEducation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    private UserInfo userInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voluntary_disclosurers_id", referencedColumnName = "id")
    private VoluntaryDisclosurers voluntaryDisclosurers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "links_id", referencedColumnName = "id")
    private Links links;

    @ManyToMany
    private List<Skills> skills;
}
