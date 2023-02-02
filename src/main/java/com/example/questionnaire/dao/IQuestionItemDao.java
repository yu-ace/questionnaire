package com.example.questionnaire.dao;

import com.example.questionnaire.model.QuestionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionItemDao extends JpaRepository<QuestionItem,Integer> {
    List<QuestionItem> findByQuestionnaireId(Integer questionnaireId);
}
