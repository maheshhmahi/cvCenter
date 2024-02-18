package com.hack.cvcenter.service.impl;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.model.LinksDetail;
import com.hack.cvcenter.model.Skills;
import com.hack.cvcenter.repository.LinksRepository;
import com.hack.cvcenter.repository.SkillRepository;
import com.hack.cvcenter.service.LinksService;
import com.hack.cvcenter.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillRepository skillRepository;

    @Override
    public Skills addOrUpdate(Skills skills) {
        try {
            return skillRepository.save(skills);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }

    @Override
    public Skills fetchSkill(String skill) {
        try {
            return skillRepository.findBySkill(skill);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }

    @Cacheable(value = "skills")
    @Override
    public List<Skills> fetchAllSkill() {
        try {
            log.info("Fetching from db");
            return skillRepository.findAll();
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }
}
