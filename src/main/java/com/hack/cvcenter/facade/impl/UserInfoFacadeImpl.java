package com.hack.cvcenter.facade.impl;

import com.hack.cvcenter.constants.ApiConstants;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.dto.UserInfoDto;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.facade.UserInfoFacade;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.model.UserInfo;
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
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class UserInfoFacadeImpl implements UserInfoFacade {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserService userService;

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public ResponseEntity<?> createUserInfo(UserInfoDto userInfoDto) {
        try {
            log.info("Check if user detail is present");
            UserDetail userDetail = userService.fetchCustomerByUuid(UUID.fromString(userInfoDto.getUserUuid()));
            if(userDetail == null) {
                throw new CustomException(ErrorMessages.USER_NOT_FOUND_ERR_MSG);
            }
            log.info("Map to model");
            UserInfo userInfo = mapper.map(userInfoDto, UserInfo.class);
            userInfo.setUserDetail(userDetail);
            userInfo.setUuid(ApiUtil.generateUuid());
            log.info("Mapping to model");
            userInfo = userInfoService.addOrUpdate(userInfo);
            if(userInfo == null) {
                throw new CustomException(ErrorMessages.USER_INFO_EXCEPTION);
            }
            userDetail.setUserInfo(userInfo);
            userService.addOrUpdateUser(userDetail);
            log.info("User info saved successfully");
            Map<String, Object> map = new HashMap<>();
            map.put(ApiConstants.FIRSTNAME, userDetail.getFirstName());
            map.put(ApiConstants.LASTNAME, userDetail.getLastName());
            map.put(ApiConstants.UUID, userDetail.getUuid());
            map.put(ApiConstants.USER_INFO_UUID, userInfo.getUuid());
            return ApiUtil.mapResponse(ApiConstants.USER_INFO_ADDED_SUCCESS_MSG, map, HttpStatus.OK);

        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }
}
