package com.hack.cvcenter.service;

import com.hack.cvcenter.model.UserDetail;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDetail addOrUpdateUser(UserDetail userDetail);

    UserDetail fetchCustomerByEmail(String email);

    UserDetail fetchCustomerByUuid(UUID uuid);

    List<UserDetail> fetchFilteredUsers(Specification<UserDetail> userDetailSpecification);
}
