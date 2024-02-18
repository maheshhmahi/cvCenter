package com.hack.cvcenter.service;

import com.hack.cvcenter.model.VoluntaryDisclosurers;
import org.springframework.stereotype.Component;

@Component
public interface VoluntaryDisclouserService {

    VoluntaryDisclosurers addOrUpdate(VoluntaryDisclosurers voluntaryDisclosurers);

}
