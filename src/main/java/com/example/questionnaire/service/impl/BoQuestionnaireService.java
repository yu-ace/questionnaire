package com.example.questionnaire.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.questionnaire.bo.BoQuestionItem;
import com.example.questionnaire.bo.BoQuestionItemContent;
import com.example.questionnaire.bo.BoQuestionnaire;
import com.example.questionnaire.dao.IQuestionItemContentDao;
import com.example.questionnaire.dao.IQuestionItemDao;
import com.example.questionnaire.dao.IQuestionnaireDao;
import com.example.questionnaire.model.QuestionItem;
import com.example.questionnaire.model.QuestionItemContent;
import com.example.questionnaire.model.Questionnaire;
import com.example.questionnaire.service.IBoQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoQuestionnaireService implements IBoQuestionnaireService {

    @Autowired
    IQuestionnaireDao questionnaireDao;
    @Autowired
    IQuestionItemDao questionItemDao;
    @Autowired
    IQuestionItemContentDao questionItemContentDao;

    /**
     * {
     * 	"name": "这个",
     * 	"creatorId": 3,
     * 	"questionItem": [{
     * 		"titel": "性别",
     * 		"questionnairId": 1,
     * 		"questionType": 1,
     * 		"questionItemContent": [{
     * 			"questionItemId": 1,
     * 			"content": "男"
     *                }, {
     * 			"questionItemId": 1,
     * 			"content": "女"
     *        }]* 	}]
     * }
     */

    @Override
    public BoQuestionnaire createBoQuestionnaire(BoQuestionnaire boQuestionnaire) throws Exception{
        Questionnaire questionnaire = BeanUtil.copyProperties(boQuestionnaire, Questionnaire.class);
        //创建问卷
        Questionnaire questionnaire1 = new Questionnaire();
        questionnaire1.setName(questionnaire.getName());
        questionnaire1.setCreatorId(questionnaire.getCreatorId());
        questionnaire1.setCreateTime(new Date());
        Questionnaire questionnaire2 = questionnaireDao.save(questionnaire1);
        //创建问卷题目列表
        List<BoQuestionItem> boQuestionItemList = boQuestionnaire.getBoQuestionItemList();
        List<QuestionItemContent> questionItemContentList = null;
        for(BoQuestionItem boQuestionItem:boQuestionItemList){
            List<BoQuestionItemContent> boQuestionItemContentList = boQuestionItem.getBoQuestionItemContentList();
            questionItemContentList =  BeanUtil.copyToList(boQuestionItemContentList, QuestionItemContent.class);
        }

        List<QuestionItem> questionItemList = BeanUtil.copyToList(boQuestionItemList, QuestionItem.class);
        for(QuestionItem questionItem:questionItemList){
            QuestionItem questionItem1 = new QuestionItem();
            questionItem1.setTitle(questionItem.getTitle());
            questionItem1.setQuestionType(questionItem.getQuestionType());
            questionItem1.setQuestionnaireId(questionnaire2.getId());
            QuestionItem questionItem2 = questionItemDao.save(questionItem1);

            //创建选项
            if(questionItemContentList != null){
                for(QuestionItemContent questionItemContent:questionItemContentList){
                    QuestionItemContent questionItemContent1 = new QuestionItemContent();
                    questionItemContent1.setContent(questionItemContent.getContent());
                    questionItemContent1.setQuestionItemId(questionItem2.getId());
                    questionItemContentDao.save(questionItemContent1);
                }
            }else {
                throw new Exception("选项不存在");
            }
        }

        BoQuestionnaire boQuestionnaire1 = BeanUtil.copyProperties(questionnaire2, BoQuestionnaire.class);
        return boQuestionnaire1;
    }

    @Override
    public List<BoQuestionnaire> getBoQuestionnaireListByCreatorId(Integer creatorId, Pageable pageable) {
        Page<Questionnaire> questionnairePage = questionnaireDao.findByCreatorId(creatorId, pageable);
        List<BoQuestionnaire> boQuestionnaireList = new ArrayList<>();
        for(Questionnaire questionnaire:questionnairePage){
            BoQuestionnaire boQuestionnaire = BeanUtil.copyProperties(questionnaire, BoQuestionnaire.class);
            boQuestionnaireList.add(boQuestionnaire);
        }
        return boQuestionnaireList;
    }

    @Override
    public BoQuestionnaire getBoQuestionnaire(Integer id) {
        Questionnaire questionnaire = questionnaireDao.getReferenceById(id);
        BoQuestionnaire boQuestionnaire = BeanUtil.copyProperties(questionnaire, BoQuestionnaire.class);
        return boQuestionnaire;
    }


    @Override
    public List<BoQuestionItem> getBoQuestionItemListByBoQuestionnaireId(Integer boQuestionnaireId) {
        List<QuestionItem> questionItemList = questionItemDao.findByQuestionnaireId(boQuestionnaireId);
        List<BoQuestionItem> boQuestionItemList = BeanUtil.copyToList(questionItemList, BoQuestionItem.class);
        return boQuestionItemList;
    }

    @Override
    public List<BoQuestionItem> getBoQuestionItemListByBoQuestionnaireIdAndBoQuestionnaireType
            (Integer boQuestionnaireId, Integer boQuestionType) {
        List<QuestionItem> questionItemList = questionItemDao
                .findByQuestionnaireIdAndQuestionType(boQuestionnaireId,boQuestionType);
        List<BoQuestionItem> boQuestionItemList = BeanUtil.copyToList(questionItemList, BoQuestionItem.class);
        return boQuestionItemList;
    }




    @Override
    public List<BoQuestionItemContent> getBoQuestionItemContentListByBoQuestionItemId(Integer boQuestionItemId) {
        List<QuestionItemContent> questionItemContentList = questionItemContentDao
                .findByQuestionItemId(boQuestionItemId);
        List<BoQuestionItemContent> boQuestionItemContentList = BeanUtil.copyToList
                (questionItemContentList, BoQuestionItemContent.class);
        return boQuestionItemContentList;
    }
}
