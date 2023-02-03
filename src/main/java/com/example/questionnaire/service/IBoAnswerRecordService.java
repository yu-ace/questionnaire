package com.example.questionnaire.service;

import com.example.questionnaire.bo.BoAnswerRecord;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IBoAnswerRecordService {
    BoAnswerRecord createBoAnswerRecord(BoAnswerRecord boAnswerRecord);
    BoAnswerRecord getBoAnswerRecord(Integer id);
    List<BoAnswerRecord> getBoAnswerRecordByRespondentId(Integer respondentId, Pageable pageable);
    List<BoAnswerRecord> getBoAnswerRecordByQuestionnaireId(Integer questionnaireId,Pageable pageable);

}
