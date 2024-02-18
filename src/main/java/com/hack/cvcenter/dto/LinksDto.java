package com.hack.cvcenter.dto;

import com.hack.cvcenter.constants.ErrorMessages;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LinksDto {

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String userUuid;

    @NotEmpty(message = ErrorMessages.IS_REQUIRED)
    private String resumeUrl;
    private String linkedInUrl;
    private String githubUrl;

}
