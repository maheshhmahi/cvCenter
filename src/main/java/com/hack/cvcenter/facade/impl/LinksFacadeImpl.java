package com.hack.cvcenter.facade.impl;

import com.hack.cvcenter.constants.ApiConstants;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.dto.LinksDto;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.facade.LinksFacade;
import com.hack.cvcenter.model.LinksDetail;
import com.hack.cvcenter.model.UserDetail;
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
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class LinksFacadeImpl implements LinksFacade {

    @Autowired
    LinksService linksService;

    @Autowired
    UserService userService;

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public ResponseEntity<?> createLinks(LinksDto linksDto) {
        log.info("Check if user detail is present");
        UserDetail userDetail = userService.fetchCustomerByUuid(UUID.fromString(linksDto.getUserUuid()));
        if(userDetail == null) {
            throw new CustomException(ErrorMessages.USER_NOT_FOUND_ERR_MSG);
        }
        log.info("Map to model");
        LinksDetail linksDetail = mapper.map(linksDto, LinksDetail.class);
        linksDetail.setUuid(ApiUtil.generateUuid());
        linksDetail.setUserDetail(userDetail);
        linksDetail = linksService.addOrUpdate(linksDetail);
        if(linksDetail == null) {
            throw new CustomException(ErrorMessages.LINKS_EXCEPTION);
        }
        log.info("Links saved successfully");
        userDetail.setLinksDetail(linksDetail);
        userService.addOrUpdateUser(userDetail);

        Map<String, Object> map = new HashMap<>();
        map.put(ApiConstants.FIRSTNAME, userDetail.getFirstName());
        map.put(ApiConstants.LASTNAME, userDetail.getLastName());
        map.put(ApiConstants.UUID, userDetail.getUuid());
        map.put(ApiConstants.LINKS_UUID, linksDetail.getUuid());

        return ApiUtil.mapResponse(ApiConstants.LINKS_ADDED_SUCCESS_MSG, map, HttpStatus.OK);
    }
}
