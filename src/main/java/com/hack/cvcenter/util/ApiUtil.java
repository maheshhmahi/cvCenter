package com.hack.cvcenter.util;

import com.hack.cvcenter.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ApiUtil {

    public static ResponseEntity<?> mapResponse(String message, Map map, HttpStatus httpStatus) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(message);
        responseDto.setData(map);
        return new ResponseEntity<>(responseDto, httpStatus);
    }

    public static ResponseEntity<?> mapResponse(String message, List list, HttpStatus httpStatus) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(message);
        responseDto.setData(list);
        return new ResponseEntity<>(responseDto, httpStatus);
    }

    public static Integer findDateDifference(String startDate, String endDate) {
        LocalDate dateOne = LocalDate.parse(startDate);
        LocalDate dateTwo = LocalDate.parse(endDate);

        Period period = Period.between(dateOne, dateTwo);

        return period.getYears();
    }
    public static UUID generateUuid() {
        return UUID.randomUUID();
    }
}
