package com.hack.cvcenter.controller;

import com.hack.cvcenter.dto.VolountaryDisclouserDto;
import com.hack.cvcenter.facade.VoluntaryDisclouserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/voluntary-disclouser")
public class VoluntaryDisclouserController {

    @Autowired
    VoluntaryDisclouserFacade voluntaryDisclouserFacade;

    @PostMapping("/create")
    public ResponseEntity<?> createVoluntaryDisclouser(@Valid @RequestBody VolountaryDisclouserDto volountaryDisclouserDto) {
        return voluntaryDisclouserFacade.createVoluntaryDisclouser(volountaryDisclouserDto);
    }

}
