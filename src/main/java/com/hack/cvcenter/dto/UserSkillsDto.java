package com.hack.cvcenter.dto;

import com.hack.cvcenter.constants.ErrorMessages;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class UserSkillsDto {

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String userUuid;

    private Set<String> skills;

}
