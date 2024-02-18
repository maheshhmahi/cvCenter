package com.hack.cvcenter.controller;

import com.hack.cvcenter.dto.UserExperienceDto;
import com.hack.cvcenter.dto.UserInfoDto;
import com.hack.cvcenter.facade.UserExperienceFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/experience")
@CrossOrigin("*")
public class UserExperienceController {

    @Autowired
    UserExperienceFacade userExperienceFacade;

    @PostMapping("/create")
    public ResponseEntity<?> createUserExp(@Valid @RequestBody UserExperienceDto userExperienceDto) {
        return userExperienceFacade.createUserExperience(userExperienceDto);
    }
}
