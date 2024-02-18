package com.hack.cvcenter.service;

import com.hack.cvcenter.model.Skills;

public interface SkillService {

    Skills addOrUpdate(Skills skills);

    Skills fetchSkill(String skill);
}
