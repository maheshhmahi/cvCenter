package com.hack.cvcenter.facade;

import com.hack.cvcenter.dto.UserDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;

public interface UserFacade {

    ResponseEntity<?> createCustomer(UserDto userDto) throws DuplicateKeyException;
}
