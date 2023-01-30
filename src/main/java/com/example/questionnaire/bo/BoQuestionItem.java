package com.example.questionnaire.bo;

import java.util.List;

public class BoQuestionItem {
    Integer id;
    String title;
    Integer questionnaireId;
    Integer questionType;
    List<BoQuestionItemContent> boQuestionItemContentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public List<BoQuestionItemContent> getBoQuestionItemContentList() {
        return boQuestionItemContentList;
    }

    public void setBoQuestionItemContentList(List<BoQuestionItemContent> boQuestionItemContentList) {
        this.boQuestionItemContentList = boQuestionItemContentList;
    }
}
