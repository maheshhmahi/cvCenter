package com.hack.cvcenter.service.impl;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.repository.UserDetailRepository;
import com.hack.cvcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public UserDetail addOrUpdateUser(UserDetail userDetail) {
        try {
            return userDetailRepository.save(userDetail);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }

    @Override
    public UserDetail fetchCustomerByEmail(String email) {
        try {
            return userDetailRepository.findUserDetailByEmail(email);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }

    @Override
    public UserDetail fetchCustomerByUuid(UUID uuid) {
        try {
            return userDetailRepository.findUserDetailByUuid(uuid);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }

    @Override
    public List<UserDetail> fetchFilteredUsers(Specification<UserDetail> userDetailSpecification) {
        try {
            return userDetailRepository.findAll(userDetailSpecification);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }
}
