package com.hack.cvcenter.repository;

import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByUserDetail(UserDetail userDetail);
}
