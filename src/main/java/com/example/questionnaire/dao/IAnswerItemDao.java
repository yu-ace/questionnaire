package com.example.questionnaire.dao;

import com.example.questionnaire.model.AnswerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnswerItemDao extends JpaRepository<AnswerItem,Integer> {
    List<AnswerItem> findByAnswerRecordId(Integer answerRecordId);
}
