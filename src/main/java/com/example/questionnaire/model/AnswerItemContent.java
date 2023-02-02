package com.example.questionnaire.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "answer_item_content")
@DynamicUpdate
public class AnswerItemContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "answer_item_id")
    Integer answerItemId;
    @Column(name = "answer_item_content")
    String answerItemContent;

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

    public String getAnswerItemContent() {
        return answerItemContent;
    }

    public void setAnswerItemContent(String answerItemContent) {
        this.answerItemContent = answerItemContent;
    }
}
