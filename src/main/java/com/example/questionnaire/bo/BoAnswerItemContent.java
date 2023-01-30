package com.example.questionnaire.bo;

public class BoAnswerItemContent {
    Integer id;
    Integer answerItemId;
    Integer questionItemContentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnswerItemId() {
        return answerItemId;
    }

    public void setAnswerItemId(Integer answerItemId) {
        this.answerItemId = answerItemId;
    }

    public Integer getQuestionItemContentId() {
        return questionItemContentId;
    }

    public void setQuestionItemContentId(Integer questionItemContentId) {
        this.questionItemContentId = questionItemContentId;
    }
}
