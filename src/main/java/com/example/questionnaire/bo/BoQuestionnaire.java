package com.example.questionnaire.bo;

import java.util.Date;
import java.util.List;

public class BoQuestionnaire {
    Integer id;
    String name;
    Integer creatorId;
    Date createTime;
    List<BoQuestionItem> boQuestionItemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<BoQuestionItem> getBoQuestionItemList() {
        return boQuestionItemList;
    }

    public void setBoQuestionItemList(List<BoQuestionItem> boQuestionItemList) {
        this.boQuestionItemList = boQuestionItemList;
    }
}
