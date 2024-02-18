package com.hack.cvcenter.controller;

import com.hack.cvcenter.dto.LoginDto;
import com.hack.cvcenter.dto.UserDto;
import com.hack.cvcenter.dto.UserSearchDto;
import com.hack.cvcenter.facade.UserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserFacade userFacade;

    @PostMapping("/signup")
    public ResponseEntity<?> userSignUp(@Valid @RequestBody UserDto userDto) {
        return userFacade.createUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody LoginDto loginDto) {
        return userFacade.userLogin(loginDto);
    }

    @GetMapping
    public ResponseEntity<?> fetchUserDetails(@RequestParam(required = true, name = "userUuid") String userUuid) {
        return userFacade.fetchAllUserDetails(userUuid);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterUser(@ModelAttribute UserSearchDto userSearchDto) {
        return userFacade.fetchFilteredUsers(userSearchDto);
    }

}
