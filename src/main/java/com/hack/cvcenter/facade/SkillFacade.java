package com.hack.cvcenter.facade;

import com.hack.cvcenter.dto.UserSkillsDto;
import org.springframework.http.ResponseEntity;

public interface SkillFacade {

    ResponseEntity<?> addUserSkill(UserSkillsDto userSkillsDto);
}
