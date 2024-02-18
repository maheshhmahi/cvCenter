package com.hack.cvcenter.dto;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.constants.ValidationRegex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDto {

    @Email
    private String email;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    @Pattern(regexp = ValidationRegex.PASSWORD_REGEX, message = ErrorMessages.PASSWORD_REGEX_ERR_MSG)
    private String password;

}
