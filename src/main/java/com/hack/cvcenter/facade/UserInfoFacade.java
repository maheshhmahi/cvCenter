package com.hack.cvcenter.facade;

import com.hack.cvcenter.dto.LoginDto;
import com.hack.cvcenter.dto.UserDto;
import com.hack.cvcenter.dto.UserInfoDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;

public interface UserInfoFacade {

    ResponseEntity<?> createUserInfo(UserInfoDto userInfoDto);

}
