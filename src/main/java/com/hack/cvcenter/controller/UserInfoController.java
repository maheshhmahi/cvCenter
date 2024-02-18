package com.hack.cvcenter.controller;

import com.hack.cvcenter.dto.UserDto;
import com.hack.cvcenter.dto.UserInfoDto;
import com.hack.cvcenter.facade.UserInfoFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Autowired
    UserInfoFacade userInfoFacade;

    @PostMapping("/create")
    public ResponseEntity<?> createUserInfo(@Valid @RequestBody UserInfoDto userInfoDto) {
        return userInfoFacade.createUserInfo(userInfoDto);
    }
}
