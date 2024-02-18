package com.hack.cvcenter.service.impl;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.model.UserInfo;
import com.hack.cvcenter.repository.UserInfoRepository;
import com.hack.cvcenter.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Override
    public UserInfo addOrUpdate(UserInfo userInfo) {
        try {
            return userInfoRepository.save(userInfo);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }

    @Override
    public UserInfo fetchUserInfoByUserDetail(UserDetail userDetail) {
        try {
            return userInfoRepository.findByUserDetail(userDetail);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }
}
