package com.example.questionnaire.bo;

import java.util.Date;
import java.util.List;

public class BoAnswerRecord {
    Integer id;
    Integer respondentId;
    Integer questionnaireId;
    Date answerTime;
    List<BoAnswerItem> boAnswerItemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(Integer respondentId) {
        this.respondentId = respondentId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public List<BoAnswerItem> getBoAnswerItemList() {
        return boAnswerItemList;
    }

    public void setBoAnswerItemList(List<BoAnswerItem> boAnswerItemList) {
        this.boAnswerItemList = boAnswerItemList;
    }
}
