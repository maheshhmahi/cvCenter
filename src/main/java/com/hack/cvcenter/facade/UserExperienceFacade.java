package com.hack.cvcenter.facade;

import com.hack.cvcenter.dto.UserExperienceDto;
import org.springframework.http.ResponseEntity;

public interface UserExperienceFacade {

    ResponseEntity<?> createUserExperience(UserExperienceDto userExperienceDto);

}
