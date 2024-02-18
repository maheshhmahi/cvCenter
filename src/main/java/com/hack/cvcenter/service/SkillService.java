package com.hack.cvcenter.service;

import com.hack.cvcenter.model.Skills;

import java.util.List;
import java.util.Set;

public interface SkillService {

    Skills addOrUpdate(Skills skills);

    Skills fetchSkill(String skill);

    List<Skills> fetchAllSkill();
}
