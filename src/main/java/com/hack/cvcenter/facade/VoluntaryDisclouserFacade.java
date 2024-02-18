package com.hack.cvcenter.facade;

import com.hack.cvcenter.dto.VolountaryDisclouserDto;
import org.springframework.http.ResponseEntity;

public interface VoluntaryDisclouserFacade {

    ResponseEntity<?> createVoluntaryDisclouser(VolountaryDisclouserDto volountaryDisclouserDto);

}
