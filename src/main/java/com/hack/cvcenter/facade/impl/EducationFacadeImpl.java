package com.hack.cvcenter.facade.impl;

import com.hack.cvcenter.constants.ApiConstants;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.dto.EducationDto;
import com.hack.cvcenter.dto.LinksDto;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.facade.EducationFacade;
import com.hack.cvcenter.facade.LinksFacade;
import com.hack.cvcenter.model.Education;
import com.hack.cvcenter.model.LinksDetail;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.service.EducationService;
import com.hack.cvcenter.service.LinksService;
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
public class EducationFacadeImpl implements EducationFacade {

    @Autowired
    EducationService educationService;

    @Autowired
    UserService userService;

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public ResponseEntity<?> createEducation(EducationDto educationDto) {
        try {
            log.info("Check if user detail is present");
            UserDetail userDetail = userService.fetchCustomerByUuid(UUID.fromString(educationDto.getUserUuid()));
            if(userDetail == null) {
                throw new CustomException(ErrorMessages.USER_NOT_FOUND_ERR_MSG);
            }
            log.info("Map to model");
            Education education = mapper.map(educationDto, Education.class);
            education.setUuid(ApiUtil.generateUuid());
            education.setUserDetail(userDetail);
            education = educationService.addOrUpdate(education);
            if(education == null) {
                throw new CustomException(ErrorMessages.EDUCATION_EXCEPTION);
            }
            if(userDetail.getUserEducation() == null) {
                userDetail.setUserEducation(new HashSet<>());
            }
            userDetail.getUserEducation().add(education);
            userService.addOrUpdateUser(userDetail);
            Map<String, Object> map = new HashMap<>();
            map.put(ApiConstants.FIRSTNAME, userDetail.getFirstName());
            map.put(ApiConstants.LASTNAME, userDetail.getLastName());
            map.put(ApiConstants.UUID, userDetail.getUuid());
            map.put(ApiConstants.EDUCATION_UUID, education.getUuid());
            return ApiUtil.mapResponse(ApiConstants.EDUCATION_ADDED_SUCCESS_MSG, map, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }
}
