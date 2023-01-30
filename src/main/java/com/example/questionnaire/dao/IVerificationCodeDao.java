package com.example.questionnaire.dao;

import com.example.questionnaire.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVerificationCodeDao extends JpaRepository<VerificationCode,Integer> {
    List<VerificationCode> findByCellphoneAndStatusOrderBySendTimeDesc(String cellphone,Integer status);
}
