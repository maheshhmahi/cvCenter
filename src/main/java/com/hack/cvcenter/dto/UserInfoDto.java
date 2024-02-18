package com.hack.cvcenter.dto;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.enums.AddressTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserInfoDto {

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String userUuid;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String phoneNumber;

    @NotNull(message = ErrorMessages.IS_REQUIRED)
    private AddressTypeEnum type;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String streetAddress;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String city;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String state;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String country;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String zipCode;

}
