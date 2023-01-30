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
    @Column(name = "question_item_content_id")
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
