package com.hack.cvcenter.service;

import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.model.UserInfo;
import org.springframework.stereotype.Component;

public interface UserInfoService {

    UserInfo addOrUpdate(UserInfo userInfo);
}
