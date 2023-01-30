package com.example.questionnaire.service.impl;

import com.example.questionnaire.service.ISmsService;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements ISmsService {

    @Override
    public boolean sendSms(String cellphone, String code) {
        System.out.println(code);
        return true;
    }
}
