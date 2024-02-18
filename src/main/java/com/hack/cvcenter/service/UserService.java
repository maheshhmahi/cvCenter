package com.hack.cvcenter.service;

import com.hack.cvcenter.model.UserDetail;

import java.util.UUID;

public interface UserService {

    UserDetail addOrUpdateUser(UserDetail userDetail);

    UserDetail fetchCustomerByEmail(String email);

    UserDetail fetchCustomerByUuid(UUID uuid);
}
