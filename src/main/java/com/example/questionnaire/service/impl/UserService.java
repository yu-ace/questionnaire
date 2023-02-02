package com.example.questionnaire.service.impl;

import com.example.questionnaire.dao.IUserDao;
import com.example.questionnaire.model.User;
import com.example.questionnaire.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public User createUser(String cellphone) {
        User user = new User();
        user.setCellphone(cellphone);
        String a = "qwertyupasdfghjklzxcvbnmQAZWSXEDCRFVTGBYHNUJMKLP123456789";
        String name = "";
        Random random = new Random();
        for(int i = 0;i < 6;i++){
            name = name + a.charAt(random.nextInt(a.length() - 1));
        }
        user.setName(name);
        userDao.save(user);
        return user;
    }

    @Override
    public User getUserByCellphone(String cellphone) {
        return userDao.findByCellphone(cellphone);
    }

}
