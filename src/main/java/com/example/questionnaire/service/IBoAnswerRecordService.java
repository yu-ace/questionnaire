package com.example.questionnaire.service;

import com.example.questionnaire.bo.BoAnswerItem;
import com.example.questionnaire.bo.BoAnswerItemContent;
import com.example.questionnaire.bo.BoAnswerRecord;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IBoAnswerRecordService {
    BoAnswerRecord createBoAnswerRecord(BoAnswerRecord boAnswerRecord) throws Exception;
    List<BoAnswerRecord> getBoAnswerRecordByRespondentId(Integer respondentId, Pageable pageable);
    List<BoAnswerRecord> getBoAnswerRecordByQuestionnaireId(Integer questionnaireId,Pageable pageable);

    List<BoAnswerItem> getBoAnswerItemByAnswerRecordId(Integer boAnswerRecordId);

    List<BoAnswerItemContent> getBoAnswerItemContentByBoAnswerItemId(Integer boAnswerItemId);
}
