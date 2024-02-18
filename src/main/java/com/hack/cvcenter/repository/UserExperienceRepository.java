package com.hack.cvcenter.repository;

import com.hack.cvcenter.model.UserExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExperienceRepository extends JpaRepository<UserExperience, Long> {

}
