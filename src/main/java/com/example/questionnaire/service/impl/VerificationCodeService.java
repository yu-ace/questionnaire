package com.example.questionnaire.service.impl;

import com.example.questionnaire.dao.IVerificationCodeDao;
import com.example.questionnaire.model.User;
import com.example.questionnaire.model.VerificationCode;
import com.example.questionnaire.service.ISmsService;
import com.example.questionnaire.service.IUserService;
import com.example.questionnaire.service.IVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class VerificationCodeService implements IVerificationCodeService {

    @Autowired
    IVerificationCodeDao verificationCodeDao;
    @Autowired
    ISmsService smsService;
    @Autowired
    IUserService userService;

    @Override
    public void sendVerificationCode(String cellphone) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCellphone(cellphone);
        Random random = new Random();
        int code = random.nextInt(999999);
        if(code < 100000){
            code = code + 100000;
        }
        verificationCode.setCode(code+"");
        verificationCode.setSendTime(new Date());
        verificationCode.setStatus(0);
        verificationCodeDao.save(verificationCode);
        smsService.sendSms(cellphone,code+"");
    }

    @Override
    public User checkVerificationCode(String cellphone, String code) throws Exception {
        List<VerificationCode> verificationCodeList = verificationCodeDao.
                findByCellphoneAndStatusOrderBySendTimeDesc(cellphone, 0);
        if(!verificationCodeList.isEmpty()){
            VerificationCode verificationCode = verificationCodeList.get(0);
            if(new Date().getTime() - verificationCode.getSendTime().getTime() >= 5*60*1000){
                throw new Exception("验证码已过期！");
            }
            if(code.equals(verificationCode.getCode())){
                for(VerificationCode verificationCode1:verificationCodeList){
                    verificationCode1.setStatus(1);
                }
                verificationCodeDao.saveAll(verificationCodeList);
                User user = userService.getUserByCellphone(cellphone);
                if(user == null){
                    User user1 = userService.createUser(cellphone);
                    return user1;
                }else{
                    return user;
                }
            }else{
                throw new Exception("验证码错误！");
            }
        }else{
            throw new Exception("验证码不存在！");
        }
    }
}
