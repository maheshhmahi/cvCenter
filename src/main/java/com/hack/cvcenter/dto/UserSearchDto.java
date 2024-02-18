package com.hack.cvcenter.dto;

import com.hack.cvcenter.enums.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Set;

@Data
public class UserSearchDto {

    RoleEnum role;
    Integer yearOfExp;
    String state;
    Set<String> skills;

}
