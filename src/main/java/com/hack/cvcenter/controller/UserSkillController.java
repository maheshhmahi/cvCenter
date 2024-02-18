package com.hack.cvcenter.controller;

import com.hack.cvcenter.dto.UserSkillsDto;
import com.hack.cvcenter.facade.SkillFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/skill")
@CrossOrigin("*")
public class UserSkillController {

    @Autowired
    SkillFacade skillFacade;

    @PostMapping("/add")
    public ResponseEntity<?> addSkills(@Valid @RequestBody UserSkillsDto userSkillsDto) {
        return skillFacade.addUserSkill(userSkillsDto);
    }

    @GetMapping
    public ResponseEntity<?> fetchSkills() {
        return skillFacade.fetchAllSkills();
    }

}
