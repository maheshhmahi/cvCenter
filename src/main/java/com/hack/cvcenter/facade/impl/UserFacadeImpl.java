package com.hack.cvcenter.facade.impl;

import com.hack.cvcenter.constants.ApiConstants;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.dto.UserDto;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.facade.UserFacade;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.service.UserService;
import com.hack.cvcenter.util.ApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class UserFacadeImpl implements UserFacade {

    @Autowired
    UserService userService;

    private static final ModelMapper mapper = new ModelMapper();
    @Override
    public ResponseEntity<?> createCustomer(UserDto userDto) throws DuplicateKeyException {
        try {
            log.info("Check if duplicate user present");
            if(userService.fetchCustomerByEmail(userDto.getEmail()) != null) {
                throw new DuplicateKeyException(ErrorMessages.USER_EXISTS_ERR_MSG);
            }
            log.info("Check if password and confirm password matches");
            if(!validatePassword(userDto.getPassword(), userDto.getConfirmPassword())) {
                throw new CustomException(ErrorMessages.PASSWORD_NOT_MATCH_ERR_MSG);
            }
            log.info("map dto to model");
            UserDetail userDetail = mapper.map(userDto, UserDetail.class);
            userDetail.setUuid(ApiUtil.generateUuid());
            userDetail = userService.addOrUpdateUser(userDetail);

            log.info("Created user");
            Map<String, String> map = new HashMap<>();
            map.put(ApiConstants.EMAIL, userDetail.getEmail());
            map.put(ApiConstants.UUID, String.valueOf(userDetail.getUuid()));

            return ApiUtil.mapResponse(ApiConstants.USERCREATESUCCESS, map, HttpStatus.OK);

        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }

    private boolean validatePassword(String password, String validatePassword) {
        if(password.equals(validatePassword)) {
            return true;
        }
        return false;
    }
}
