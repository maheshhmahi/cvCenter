package com.hack.cvcenter.controller;

import com.hack.cvcenter.dto.LinksDto;
import com.hack.cvcenter.facade.LinksFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/links")
@CrossOrigin("*")
public class LinksController {

    @Autowired
    LinksFacade linksFacade;

    @PostMapping("/create")
    public ResponseEntity<?> createLinks(@Valid @RequestBody LinksDto linksDto) {
        return linksFacade.createLinks(linksDto);
    }

}
