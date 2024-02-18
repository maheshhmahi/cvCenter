package com.hack.cvcenter.service.impl;

import com.hack.cvcenter.constants.ErrorMessages;
import com.hack.cvcenter.exception.CustomException;
import com.hack.cvcenter.model.LinksDetail;
import com.hack.cvcenter.repository.LinksRepository;
import com.hack.cvcenter.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinksServiceImpl implements LinksService {

    @Autowired
    LinksRepository linksRepository;

    @Override
    public LinksDetail addOrUpdate(LinksDetail linksDetail) {
        try {
            return linksRepository.save(linksDetail);
        } catch (Exception e) {
            throw new CustomException(ErrorMessages.DB_CONNECTION_EXCEPTION + e.getMessage());
        }
    }
}
