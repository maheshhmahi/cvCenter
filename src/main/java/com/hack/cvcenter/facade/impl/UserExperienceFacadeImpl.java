package com.hack.cvcenter.facade.impl;

import com.hack.cvcenter.constants.ApiConstants;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.dto.UserExperienceDto;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.facade.UserExperienceFacade;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.model.UserExperience;
import com.hack.cvcenter.model.UserInfo;
import com.hack.cvcenter.service.UserExperienceService;
import com.hack.cvcenter.service.UserInfoService;
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
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
public class UserExperienceFacadeImpl implements UserExperienceFacade {

    @Autowired
    UserExperienceService userExperienceService;

    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

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
            AtomicReference<Integer> yearOfExp = new AtomicReference<>(0);
            userExperienceDto.getExperienceDetails().stream().forEach(experienceDto -> {
                yearOfExp.updateAndGet(v -> v + experienceDto.getYearOfExp());
                UserExperience userExperience = mapper.map(experienceDto, UserExperience.class);
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
            });

            UserInfo userInfo = userInfoService.fetchUserInfoByUserDetail(userDetail);
            userInfo.setTotalYearOfExp(yearOfExp.get());

            userInfoService.addOrUpdate(userInfo);

            Map<String, Object> map = new HashMap<>();
            map.put(ApiConstants.FIRSTNAME, userDetail.getFirstName());
            map.put(ApiConstants.LASTNAME, userDetail.getLastName());
            map.put(ApiConstants.UUID, userDetail.getUuid());
            return ApiUtil.mapResponse(ApiConstants.USER_EXP_ADDED_SUCCESS_MSG, map, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }
}
