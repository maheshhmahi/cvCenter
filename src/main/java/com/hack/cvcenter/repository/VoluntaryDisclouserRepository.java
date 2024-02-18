package com.hack.cvcenter.repository;

import com.hack.cvcenter.model.VoluntaryDisclosurers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoluntaryDisclouserRepository extends JpaRepository<VoluntaryDisclosurers, Long> {

}
