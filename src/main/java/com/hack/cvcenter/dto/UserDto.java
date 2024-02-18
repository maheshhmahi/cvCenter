package com.hack.cvcenter.dto;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.constants.ValidationRegex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {

    @Email
    private String email;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    @Pattern(regexp = ValidationRegex.PASSWORD_REGEX, message = ErrorMessages.PASSWORD_REGEX_ERR_MSG)
    private String password;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    @Pattern(regexp = ValidationRegex.PASSWORD_REGEX, message = ErrorMessages.PASSWORD_REGEX_ERR_MSG)
    private String confirmPassword;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String firstName;
    private String lastName;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    @Pattern(regexp = ValidationRegex.DATE_REGEX, message = ErrorMessages.DOB_ERR_MSG)
    private String dob;

    @NotNull(message = ErrorMessages.IS_REQUIRED)
    private Boolean isRecruiter;

}
