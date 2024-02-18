package com.hack.cvcenter.service.impl;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.model.VoluntaryDisclosurers;
import com.hack.cvcenter.repository.VoluntaryDisclouserRepository;
import com.hack.cvcenter.service.VoluntaryDisclouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoluntaryDisclouserServiceImpl implements VoluntaryDisclouserService {

    @Autowired
    VoluntaryDisclouserRepository voluntaryDisclouserRepository;

    @Override
    public VoluntaryDisclosurers addOrUpdate(VoluntaryDisclosurers voluntaryDisclosurers) {
        try {
            return voluntaryDisclouserRepository.save(voluntaryDisclosurers);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }

}
