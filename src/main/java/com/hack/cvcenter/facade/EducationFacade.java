package com.hack.cvcenter.facade;

import com.hack.cvcenter.dto.UserEducationDto;
import org.springframework.http.ResponseEntity;

public interface EducationFacade {

    ResponseEntity<?> createEducation(UserEducationDto userEducationDto);

}
