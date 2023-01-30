package com.example.questionnaire.dao;

import com.example.questionnaire.model.AnswerItemContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnswerItemContentDao extends JpaRepository<AnswerItemContent,Integer> {
    List<AnswerItemContent> findByAnswerItemId(Integer answerItemId);
}
