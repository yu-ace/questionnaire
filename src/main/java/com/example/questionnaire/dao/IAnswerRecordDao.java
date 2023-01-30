package com.example.questionnaire.dao;

import com.example.questionnaire.model.AnswerRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRecordDao extends JpaRepository<AnswerRecord,Integer> {
    Page<AnswerRecord> findByRespondentIdOrderByAnswerTimeDesc(Integer respondentId, Pageable pageable);
    Page<AnswerRecord> findByQuestionnaireIdOrderByAnswerTimeDesc(Integer questionnaireId,Pageable pageable);
}
