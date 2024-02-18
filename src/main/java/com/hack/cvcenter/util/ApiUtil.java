package com.hack.cvcenter.util;

import com.hack.cvcenter.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public class ApiUtil {

    public static ResponseEntity<?> mapResponse(String message, Map map, HttpStatus httpStatus) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(message);
        responseDto.setData(map);
        return new ResponseEntity<>(responseDto, httpStatus);
    }

    public static UUID generateUuid() {
        return UUID.randomUUID();
    }
}
