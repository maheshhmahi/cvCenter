package com.hack.cvcenter.facade.impl;

import com.hack.cvcenter.constants.ApiConstants;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.dto.VolountaryDisclouserDto;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.facade.VoluntaryDisclouserFacade;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.model.UserInfo;
import com.hack.cvcenter.model.VoluntaryDisclosurers;
import com.hack.cvcenter.service.UserService;
import com.hack.cvcenter.service.VoluntaryDisclouserService;
import com.hack.cvcenter.util.ApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class VoluntaryDisclouserFacadeImpl implements VoluntaryDisclouserFacade {

    @Autowired
    VoluntaryDisclouserService voluntaryDisclouserService;

    @Autowired
    UserService userService;

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public ResponseEntity<?> createVoluntaryDisclouser(VolountaryDisclouserDto volountaryDisclouserDto) {
        try {
            log.info("Check if user detail is present");
            UserDetail userDetail = userService.fetchCustomerByUuid(UUID.fromString(volountaryDisclouserDto.getUserUuid()));
            if(userDetail == null) {
                throw new CustomException(ErrorMessages.USER_NOT_FOUND_ERR_MSG);
            }
            log.info("Map to model");
            VoluntaryDisclosurers voluntaryDisclosurers = mapper.map(volountaryDisclouserDto, VoluntaryDisclosurers.class);
            voluntaryDisclosurers.setUserDetail(userDetail);
            voluntaryDisclosurers.setUuid(ApiUtil.generateUuid());
            voluntaryDisclosurers = voluntaryDisclouserService.addOrUpdate(voluntaryDisclosurers);
            if(voluntaryDisclosurers == null) {
                throw new CustomException(ErrorMessages.VOLUNTARY_DISCLOUSER_EXCEPTION);
            }
            log.info("Voluntary disclouser added successfully");
            userDetail.setVoluntaryDisclosurers(voluntaryDisclosurers);
            userService.addOrUpdateUser(userDetail);
            Map<String, Object> map = new HashMap<>();
            map.put(ApiConstants.EMAIL, userDetail.getEmail());
            map.put(ApiConstants.UUID, userDetail.getUuid());
            map.put(ApiConstants.VOLUNTARY_DISCLOUSER_UUID, voluntaryDisclosurers.getUuid());
            return ApiUtil.mapResponse(ApiConstants.VOLUNTARY_DISCLOUSER_ADDED_SUCCESS_MSG, map, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }

}
