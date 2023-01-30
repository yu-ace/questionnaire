package com.example.questionnaire.dao;

import com.example.questionnaire.model.Questionnaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionnaireDao extends JpaRepository<Questionnaire,Integer> {
    Page<Questionnaire> findByCreatorId(Integer creatorId, Pageable pageable);
}
