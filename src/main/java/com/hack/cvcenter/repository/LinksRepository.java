package com.hack.cvcenter.repository;

import com.hack.cvcenter.model.LinksDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinksRepository extends JpaRepository<LinksDetail, Long> {

}
