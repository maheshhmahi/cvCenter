package com.hack.cvcenter.facade;

import com.hack.cvcenter.dto.LinksDto;
import org.springframework.http.ResponseEntity;

public interface LinksFacade {

    ResponseEntity<?> createLinks(LinksDto linksDto);

}
