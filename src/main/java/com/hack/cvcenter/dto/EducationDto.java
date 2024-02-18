package com.hack.cvcenter.dto;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.constants.ValidationRegex;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationDto {

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String userUuid;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String universityName;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String degree;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String major;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    @Pattern(regexp = ValidationRegex.DATE_REGEX, message = ErrorMessages.DOB_ERR_MSG)
    private String startDate;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    @Pattern(regexp = ValidationRegex.DATE_REGEX, message = ErrorMessages.DOB_ERR_MSG)
    private String endDate;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String gpa;

}
