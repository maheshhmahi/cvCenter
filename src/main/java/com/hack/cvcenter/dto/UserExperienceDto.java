package com.hack.cvcenter.dto;

import com.hack.cvcenter.constants.ErrorMessages;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class UserExperienceDto {

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String userUuid;

    private Set<ExperienceDto> experienceDetails;

}
