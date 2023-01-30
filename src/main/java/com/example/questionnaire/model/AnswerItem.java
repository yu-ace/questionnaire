package com.example.questionnaire.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "answer_item")
@DynamicUpdate
public class AnswerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "question_item_id")
    Integer questionItemId;
    @Column(name = "answer_record_id")
    Integer answerRecordId;

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
}
