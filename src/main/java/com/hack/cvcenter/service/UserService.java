package com.hack.cvcenter.service;

import com.hack.cvcenter.model.UserDetail;

public interface UserService {

    UserDetail addOrUpdateUser(UserDetail userDetail);

    UserDetail fetchCustomerByEmail(String email);
}
