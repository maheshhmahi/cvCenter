package com.hack.cvcenter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.constants.ValidationRegex;
import com.hack.cvcenter.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserFilterResponseDto {

    private UUID uuid;
    private String email;
    private String dob;
    private String firstName;
    private String lastName;
    private Boolean isRecruiter;

    private Set<UserExperience> userExperience;

    private Set<Education> userEducation;
    private UserInfo userInfo;

    private VoluntaryDisclosurers voluntaryDisclosurers;

    private LinksDetail linksDetail;

    private Set<Skills> skills;

}
