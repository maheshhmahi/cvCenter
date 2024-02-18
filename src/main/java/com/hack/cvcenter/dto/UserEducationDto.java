package com.hack.cvcenter.dto;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.constants.ValidationRegex;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Set;

@Data
public class UserEducationDto {

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String userUuid;

    private Set<EducationDto> educationDetails;

}
