package com.hack.cvcenter.facade.impl;

import com.hack.cvcenter.constants.ApiConstants;
import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.dto.LoginDto;
import com.hack.cvcenter.dto.UserDto;
import com.hack.cvcenter.dto.UserSearchDto;
import com.hack.cvcenter.dto.UserSkillsDto;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.facade.UserFacade;
import com.hack.cvcenter.model.UserDetail;
import com.hack.cvcenter.service.UserService;
import com.hack.cvcenter.specification.UserDetailSpecification;
import com.hack.cvcenter.util.ApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class UserFacadeImpl implements UserFacade {

    @Autowired
    UserService userService;

    private static final ModelMapper mapper = new ModelMapper();
    @Override
    public ResponseEntity<?> createUser(UserDto userDto) throws DuplicateKeyException {
        try {
            log.info("Check if duplicate user present");
            if(userService.fetchCustomerByEmail(userDto.getEmail()) != null) {
                throw new DuplicateKeyException(ErrorMessages.USER_EXISTS_ERR_MSG);
            }
            log.info("Check if password and confirm password matches");
            if(!validatePassword(userDto.getPassword(), userDto.getConfirmPassword())) {
                throw new CustomException(ErrorMessages.PASSWORD_NOT_MATCH_ERR_MSG);
            }
            log.info("map dto to model");
            UserDetail userDetail = mapper.map(userDto, UserDetail.class);
            userDetail.setUuid(ApiUtil.generateUuid());
            userDetail = userService.addOrUpdateUser(userDetail);

            log.info("Created user");
            Map<String, String> map = new HashMap<>();
            map.put(ApiConstants.FIRSTNAME, userDetail.getFirstName());
            map.put(ApiConstants.LASTNAME, userDetail.getLastName());
            map.put(ApiConstants.UUID, String.valueOf(userDetail.getUuid()));

            return ApiUtil.mapResponse(ApiConstants.USERCREATESUCCESS, map, HttpStatus.OK);

        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> userLogin(LoginDto loginDto) {
        try {
            log.info("check if user is present");
            UserDetail userDetail = userService.fetchCustomerByEmail(loginDto.getEmail());
            Map<String, Object> responseMap = new HashMap<>();
            if(userDetail == null) {
                responseMap.put(ApiConstants.ERR_FIELD, ApiConstants.USER_NOT_PRESENT);
                return ApiUtil.mapResponse(ApiConstants.USER_NOT_PRESENT, responseMap, HttpStatus.OK);
            }
            log.info("validate password");
            if(!validatePassword(userDetail.getPassword(), loginDto.getPassword())) {
                responseMap.put(ApiConstants.ERR_FIELD, ApiConstants.INVALID_PASSWORD);
                return ApiUtil.mapResponse(ApiConstants.ERR_FIELD, responseMap, HttpStatus.OK);
            }
            responseMap.put(ApiConstants.FIRSTNAME, userDetail.getFirstName());
            responseMap.put(ApiConstants.LASTNAME, userDetail.getLastName());
            responseMap.put(ApiConstants.UUID, userDetail.getUuid());
            responseMap.put(ApiConstants.IS_RECRUITER, userDetail.getIsRecruiter());
            return ApiUtil.mapResponse(ApiConstants.USERLOGINSUCCESS, responseMap, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> fetchAllUserDetails(String userUuid) {
        try {
            log.info("check if user is present");
            UserDetail userDetail = userService.fetchCustomerByUuid(UUID.fromString(userUuid));
            Map<String, Object> responseMap = new HashMap<>();
            if(userDetail == null) {
                responseMap.put(ApiConstants.ERR_FIELD, ApiConstants.USER_NOT_PRESENT);
                return ApiUtil.mapResponse(ApiConstants.USER_NOT_PRESENT, responseMap, HttpStatus.OK);
            }
            responseMap.put(ApiConstants.UUID, userDetail.getUuid());
            responseMap.put(ApiConstants.EMAIL, userDetail.getEmail());
            responseMap.put(ApiConstants.FIRSTNAME, userDetail.getFirstName());
            responseMap.put(ApiConstants.LASTNAME, userDetail.getLastName());
            responseMap.put(ApiConstants.DOB, userDetail.getDob());
            responseMap.put(ApiConstants.IS_RECRUITER, userDetail.getIsRecruiter());
            responseMap.put(ApiConstants.USER_EXPERIENCE, userDetail.getUserExperience());
            responseMap.put(ApiConstants.USER_EDUCATION, userDetail.getUserEducation());
            responseMap.put(ApiConstants.USER_INFO, userDetail.getUserInfo());
            responseMap.put(ApiConstants.VOLUNTARY_DISCLOSURES, userDetail.getVoluntaryDisclosurers());
            responseMap.put(ApiConstants.LINKS_DETAIL, userDetail.getLinksDetail());
            responseMap.put(ApiConstants.SKILLS, userDetail.getSkills());
            return ApiUtil.mapResponse(ApiConstants.USER_DETAILS_FETCHED_SUCCESS_MSG, responseMap, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> fetchFilteredUsers(UserSearchDto userSearchDto) {
        try {
            Specification<UserDetail> userDetailSpecification = Specification.where(null);
            if (userSearchDto.getRole() != null) {
                userDetailSpecification = userDetailSpecification.and(UserDetailSpecification.withRole(userSearchDto.getRole().toString()));
            }

            if (userSearchDto.getYearOfExp() != null) {
                userDetailSpecification = userDetailSpecification.and(UserDetailSpecification.withYearOfExp(userSearchDto.getYearOfExp()));
            }

            if (userSearchDto.getState() != null) {
                userDetailSpecification = userDetailSpecification.and(UserDetailSpecification.withState(userSearchDto.getState()));
            }

            if (userSearchDto.getSkills() != null && !userSearchDto.getSkills().isEmpty()) {
                userDetailSpecification = userDetailSpecification.and(UserDetailSpecification.withSkills(userSearchDto.getSkills()));
            }
            List<UserDetail> userDetailList = userService.fetchFilteredUsers(userDetailSpecification);
            return ApiUtil.mapResponse(ApiConstants.USERDETAILS, userDetailList, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.GENERAL_EXCEPTION_MSG + e.getMessage());
        }
    }

    private boolean validatePassword(String password, String validatePassword) {
        if(password.equals(validatePassword)) {
            return true;
        }
        return false;
    }
}
