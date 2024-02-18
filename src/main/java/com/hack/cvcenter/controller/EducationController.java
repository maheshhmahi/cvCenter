package com.hack.cvcenter.controller;

import com.hack.cvcenter.dto.EducationDto;
import com.hack.cvcenter.dto.LinksDto;
import com.hack.cvcenter.facade.EducationFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/education")
@CrossOrigin("*")
public class EducationController {

    @Autowired
    EducationFacade educationFacade;

    @PostMapping("/create")
    public ResponseEntity<?> createEducation(@Valid @RequestBody EducationDto educationDto) {
        return educationFacade.createEducation(educationDto);
    }

}
