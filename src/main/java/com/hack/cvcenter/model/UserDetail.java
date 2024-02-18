package com.hack.cvcenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_detail")
public class UserDetail implements Serializable {

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
    @OneToMany(fetch = FetchType.EAGER)
    private Set<UserExperience> userExperience;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Education> userEducation;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    private UserInfo userInfo;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voluntary_disclosurers_id", referencedColumnName = "id")
    private VoluntaryDisclosurers voluntaryDisclosurers;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "links_id", referencedColumnName = "id")
    @JsonIgnoreProperties("userDetail")
    private LinksDetail linksDetail;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Skills> skills;

    @Override
    public String toString() {
        return "Hello";
    }
}
