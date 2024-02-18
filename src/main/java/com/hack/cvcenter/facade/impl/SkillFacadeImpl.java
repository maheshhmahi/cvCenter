package com.hack.cvcenter.facade.impl;

import com.hack.cvcenter.constants.ApiConstants;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.dto.UserSkillsDto;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.facade.SkillFacade;
import com.hack.cvcenter.model.Skills;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.service.SkillService;
import com.hack.cvcenter.service.UserService;
import com.hack.cvcenter.util.ApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class SkillFacadeImpl implements SkillFacade {

    @Autowired
    SkillService skillService;

    @Autowired
    UserService userService;

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public ResponseEntity<?> addUserSkill(UserSkillsDto userSkillsDto) {
        try {
            log.info("Check if user detail is present");
            UserDetail userDetail = userService.fetchCustomerByUuid(UUID.fromString(userSkillsDto.getUserUuid()));
            if(userDetail == null) {
                throw new CustomException(ErrorMessages.USER_NOT_FOUND_ERR_MSG);
            }
            log.info("Map to model");
            userSkillsDto.getSkills().stream().forEach(skill -> {
                Skills skills = skillService.fetchSkill(skill);
                if(skills == null) {
                    throw new CustomException(ErrorMessages.SKILL_NOT_PRESENT_EXCPETION);
                }
                if(userDetail.getSkills() == null) {
                    userDetail.setSkills(new HashSet<>());
                }
                log.info("Add skills to users");
                userDetail.getSkills().add(skills);
                userService.addOrUpdateUser(userDetail);

                if(skills.getUsersDetails() == null) {
                    skills.setUsersDetails(new HashSet<>());
                }
                log.info("Add users to skills");
                skills.getUsersDetails().add(userDetail);
                skillService.addOrUpdate(skills);
            });
            Map<String, Object> map = new HashMap<>();
            map.put(ApiConstants.FIRSTNAME, userDetail.getFirstName());
            map.put(ApiConstants.LASTNAME, userDetail.getLastName());
            map.put(ApiConstants.UUID, userDetail.getUuid());
            return ApiUtil.mapResponse(ApiConstants.SKILLS_ADDED_SUCCESS_MSG, map, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }

}
