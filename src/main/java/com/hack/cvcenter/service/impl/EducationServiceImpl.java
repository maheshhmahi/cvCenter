package com.hack.cvcenter.service.impl;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.model.Education;
import com.hack.cvcenter.model.LinksDetail;
import com.hack.cvcenter.repository.EducationRepository;
import com.hack.cvcenter.repository.LinksRepository;
import com.hack.cvcenter.service.EducationService;
import com.hack.cvcenter.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EducationServiceImpl implements EducationService {

    @Autowired
    EducationRepository educationRepository;

    @Override
    public Education addOrUpdate(Education education) {
        try {
            return educationRepository.save(education);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }
}
