package com.example.questionnaire.service;

import com.example.questionnaire.bo.BoAnswerItem;
import com.example.questionnaire.bo.BoQuestionItem;
import com.example.questionnaire.bo.BoQuestionItemContent;
import com.example.questionnaire.bo.BoQuestionnaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBoQuestionnaireService {
    BoQuestionnaire createBoQuestionnaire(BoQuestionnaire boQuestionnaire);
    List<BoQuestionnaire> getBoQuestionnaireListByCreatorId(Integer creatorId, Pageable pageable);
    BoQuestionnaire getBoQuestionnaire(Integer id);


    List<BoQuestionItem> getBoQuestionItemListByBoQuestionnaireId(Integer boQuestionnaireId);
    List<BoQuestionItem> getBoQuestionItemListByBoQuestionnaireIdAndBoQuestionnaireType(Integer boQuestionnaireId,
                                                                                    Integer boQuestionType);


    List<BoQuestionItemContent> getBoQuestionItemContentListByBoQuestionItemId(Integer boQuestionItemId);

}
