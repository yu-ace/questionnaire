package com.example.questionnaire.dao;

import com.example.questionnaire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User,Integer> {
    User findByCellphone(String cellphone);
}
