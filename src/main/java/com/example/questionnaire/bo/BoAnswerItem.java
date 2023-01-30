package com.example.questionnaire.bo;

import java.util.List;

public class BoAnswerItem {
    Integer id;
    Integer questionItemId;
    Integer answerRecordId;
    List<BoAnswerItemContent> boAnswerItemContentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionItemId() {
        return questionItemId;
    }

    public void setQuestionItemId(Integer questionItemId) {
        this.questionItemId = questionItemId;
    }

    public Integer getAnswerRecordId() {
        return answerRecordId;
    }

    public void setAnswerRecordId(Integer answerRecordId) {
        this.answerRecordId = answerRecordId;
    }

    public List<BoAnswerItemContent> getBoAnswerItemContentList() {
        return boAnswerItemContentList;
    }

    public void setBoAnswerItemContentList(List<BoAnswerItemContent> boAnswerItemContentList) {
        this.boAnswerItemContentList = boAnswerItemContentList;
    }
}
