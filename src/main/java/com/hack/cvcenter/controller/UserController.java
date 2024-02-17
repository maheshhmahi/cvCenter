package com.hack.cvcenter.controller;

import com.hack.cvcenter.dto.UserDto;
import com.hack.cvcenter.facade.UserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserFacade userFacade;

    @PostMapping("/signup")
    public ResponseEntity<?> hello(@Valid @RequestBody UserDto userDto) {
        return userFacade.createCustomer(userDto);
    }

}
