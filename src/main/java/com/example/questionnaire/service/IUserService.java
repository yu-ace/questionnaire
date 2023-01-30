package com.example.questionnaire.service;

import com.example.questionnaire.model.User;

public interface IUserService {
    User createUser(String cellphone);
    User getUserByCellphone(String cellphone);
}
