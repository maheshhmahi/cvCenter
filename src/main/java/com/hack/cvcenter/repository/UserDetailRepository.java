package com.hack.cvcenter.repository;

import com.hack.cvcenter.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    UserDetail findUserDetailByEmail(String email);
}
