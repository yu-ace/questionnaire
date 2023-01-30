package com.example.questionnaire.dao;

import com.example.questionnaire.model.QuestionItemContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionItemContentDao extends JpaRepository<QuestionItemContent,Integer> {
    List<QuestionItemContent> findByQuestionItemId(Integer questionItemId);
}
