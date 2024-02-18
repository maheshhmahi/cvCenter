package com.hack.cvcenter.service.impl;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.model.UserExperience;
import com.hack.cvcenter.repository.UserExperienceRepository;
import com.hack.cvcenter.service.UserExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExperienceServiceImpl implements UserExperienceService {

    @Autowired
    UserExperienceRepository userExperienceRepository;

    @Override
    public UserExperience addOrUpdate(UserExperience userExperience) {
        try {
            return userExperienceRepository.save(userExperience);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }
}
