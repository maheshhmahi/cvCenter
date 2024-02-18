package com.hack.cvcenter.repository;

import com.hack.cvcenter.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skills, Long> {

    Skills findBySkill(String skill);

}
