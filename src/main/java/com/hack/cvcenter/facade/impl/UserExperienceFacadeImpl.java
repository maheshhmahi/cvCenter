package com.hack.cvcenter.facade.impl;

import com.hack.cvcenter.constants.ApiConstants;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.dto.UserExperienceDto;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.facade.UserExperienceFacade;
import com.hack.cvcenter.model.Education;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.model.UserExperience;
import com.hack.cvcenter.service.UserExperienceService;
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
public class UserExperienceFacadeImpl implements UserExperienceFacade {

    @Autowired
    UserExperienceService userExperienceService;

    @Autowired
    UserService userService;

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public ResponseEntity<?> createUserExperience(UserExperienceDto userExperienceDto) {
        try {
            log.info("Check if user detail is present");
            UserDetail userDetail = userService.fetchCustomerByUuid(UUID.fromString(userExperienceDto.getUserUuid()));
            if(userDetail == null) {
                throw new CustomException(ErrorMessages.USER_NOT_FOUND_ERR_MSG);
            }
            log.info("Map to model");
            UserExperience userExperience = mapper.map(userExperienceDto, UserExperience.class);
            userExperience.setUuid(ApiUtil.generateUuid());
            userExperience.setUserDetail(userDetail);
            userExperience = userExperienceService.addOrUpdate(userExperience);
            if(userExperience == null) {
                throw new CustomException(ErrorMessages.USER_EXPERIENCE_EXCEPTION);
            }
            if(userDetail.getUserExperience() == null) {
                userDetail.setUserExperience(new HashSet<>());
            }
            userDetail.getUserExperience().add(userExperience);
            userService.addOrUpdateUser(userDetail);
            Map<String, Object> map = new HashMap<>();
            map.put(ApiConstants.FIRSTNAME, userDetail.getFirstName());
            map.put(ApiConstants.LASTNAME, userDetail.getLastName());
            map.put(ApiConstants.UUID, userDetail.getUuid());
            map.put(ApiConstants.USER_EXPERIENCE_UUID, userExperience.getUuid());
            return ApiUtil.mapResponse(ApiConstants.USER_EXP_ADDED_SUCCESS_MSG, map, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }
}
