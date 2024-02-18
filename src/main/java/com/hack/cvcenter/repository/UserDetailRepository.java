package com.hack.cvcenter.repository;

import com.hack.cvcenter.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    UserDetail findUserDetailByEmail(String email);

    UserDetail findUserDetailByUuid(UUID uuid);
}
