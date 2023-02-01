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
     * 	"creatorId": 4,
     * 	"boQuestionItemList": [{
     * 		"title": "性别",
     * 		"questionType": 1,
     * 		"boQuestionItemContentList": [{
     * 				"content": "男"
     *                        },
     *            {
     * 				"content": "女"
     *            }
     * 		]
     * 	},{
     * 		"title": "喜欢吗",
     * 		"questionType": 3,
     * 		"boQuestionItemContentList": [{
     * 				"content": ""
     *            }
     * 		]
     * 	},{
     * 		"title": "多选",
     * 		"questionType": 2,
     * 		"boQuestionItemContentList": [{
     * 				"content": "q"
     *            },
     *            {
     * 				"content": "w"
     *            },
     *            {
     * 				"content": "e"
     *            },
     *            {
     * 				"content": "r"
     *            },
     *            {
     * 				"content": "t"
     *            }
     * 		]
     * 	}]
     * }
     */

    @Override
    public BoQuestionnaire createBoQuestionnaire(BoQuestionnaire boQuestionnaire){
        Questionnaire questionnaire = BeanUtil.copyProperties(boQuestionnaire, Questionnaire.class);
        //创建问卷
        Questionnaire questionnaire1 = new Questionnaire();
        questionnaire1.setName(questionnaire.getName());
        questionnaire1.setCreatorId(questionnaire.getCreatorId());
        questionnaire1.setCreateTime(new Date());
        questionnaireDao.save(questionnaire1);
        //创建问卷题目列表
        List<BoQuestionItem> boQuestionItemList = boQuestionnaire.getBoQuestionItemList();
        //创建对应的选项
        List<List<QuestionItemContent>> questionItemContentList = new ArrayList<>();
        for(BoQuestionItem boQuestionItem:boQuestionItemList){
            List<BoQuestionItemContent> boQuestionItemContentList = boQuestionItem.getBoQuestionItemContentList();
            List<QuestionItemContent> questionItemContentList1 =  BeanUtil.copyToList(boQuestionItemContentList,
                    QuestionItemContent.class);
            questionItemContentList.add(questionItemContentList1);
        }

        List<QuestionItem> questionItemList = BeanUtil.copyToList(boQuestionItemList, QuestionItem.class);
        //每一个题目对应一个选项数组
        for(int i = 0;i < questionItemList.size();i++){
            QuestionItem questionItem1 = new QuestionItem();
            questionItem1.setTitle(questionItemList.get(i).getTitle());
            questionItem1.setQuestionType(questionItemList.get(i).getQuestionType());
            questionItem1.setQuestionnaireId(questionnaire1.getId());
            questionItemDao.save(questionItem1);

            for(QuestionItemContent questionItemContent:questionItemContentList.get(i)){
                questionItemContent.setQuestionItemId(questionItem1.getId());
                questionItemContentDao.save(questionItemContent);
            }

        }

        BoQuestionnaire boQuestionnaire1 = BeanUtil.copyProperties(questionnaire1, BoQuestionnaire.class);
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
