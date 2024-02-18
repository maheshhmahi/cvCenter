package com.hack.cvcenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "voluntary_disclosurers")
public class VoluntaryDisclosurers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    private String gender;
    private String ethinicity;
    private String veteranStatus;
    private String disabled;

    @JsonBackReference
    @OneToOne(mappedBy = "voluntaryDisclosurers")
    private UserDetail userDetail;

}
