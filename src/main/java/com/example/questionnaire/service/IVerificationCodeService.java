package com.example.questionnaire.service;

import com.example.questionnaire.model.User;

public interface IVerificationCodeService {
    void sendVerificationCode(String cellphone);
    User checkVerificationCode(String cellphone, String code) throws Exception;
}
