package com.hack.cvcenter.repository;

import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.specification.UserDetailSpecification;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long>, JpaSpecificationExecutor<UserDetail> {

    UserDetail findUserDetailByEmail(String email);

    UserDetail findUserDetailByUuid(UUID uuid);

    List<UserDetail> findAll();
}
