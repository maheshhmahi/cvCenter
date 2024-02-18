package com.hack.cvcenter.dto;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.constants.ValidationRegex;
import com.hack.cvcenter.enums.EthinicityEnum;
import com.hack.cvcenter.enums.GenderEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VolountaryDisclouserDto {

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String userUuid;

    @NotNull(message = ErrorMessages.IS_REQUIRED)
    private GenderEnum gender;

    @NotNull(message = ErrorMessages.IS_REQUIRED)
    private EthinicityEnum ethinicity;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    @Pattern(regexp = ValidationRegex.YES_NO_REGEX, message = ErrorMessages.YES_NO_ERR_MSG)
    private String veteranStatus;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    @Pattern(regexp = ValidationRegex.YES_NO_REGEX, message = ErrorMessages.YES_NO_ERR_MSG)
    private String disabled;

}
