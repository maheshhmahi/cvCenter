package com.hack.cvcenter.facade;

import com.hack.cvcenter.dto.EducationDto;
import org.springframework.http.ResponseEntity;

public interface EducationFacade {

    ResponseEntity<?> createEducation(EducationDto educationDto);

}
