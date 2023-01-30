package com.example.questionnaire.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.questionnaire.bo.BoAnswerItem;
import com.example.questionnaire.bo.BoAnswerItemContent;
import com.example.questionnaire.bo.BoAnswerRecord;
import com.example.questionnaire.dao.IAnswerItemContentDao;
import com.example.questionnaire.dao.IAnswerItemDao;
import com.example.questionnaire.dao.IAnswerRecordDao;
import com.example.questionnaire.model.AnswerItem;
import com.example.questionnaire.model.AnswerItemContent;
import com.example.questionnaire.model.AnswerRecord;
import com.example.questionnaire.service.IBoAnswerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoAnswerRecordService implements IBoAnswerRecordService {

    @Autowired
    IAnswerRecordDao answerRecordDao;
    @Autowired
    IAnswerItemDao answerItemDao;
    @Autowired
    IAnswerItemContentDao answerItemContentDao;


    /**
     * {
     * 	"respondentId": 1,
     * 	"questionnaireId": 3,
     * 	"answerItem": [{
     * 		"questionItemId": 2,
     * 		"answerRecordId": 1,
     * 		"answerItemContent": [{
     * 			"answerItemId": 1,
     * 			"questionItemContentId": 2
     *                }]* 	}]
     * }
     */

    @Override
    public BoAnswerRecord createBoAnswerRecord(BoAnswerRecord boAnswerRecord) throws Exception{
        AnswerRecord answerRecord = BeanUtil.copyProperties(boAnswerRecord, AnswerRecord.class);

        AnswerRecord answerRecord1 = new AnswerRecord();
        answerRecord1.setRespondentId(answerRecord.getRespondentId());
        answerRecord1.setQuestionnaireId(answerRecord.getQuestionnaireId());
        answerRecord1.setAnswerTime(new Date());
        AnswerRecord answerRecord2 = answerRecordDao.save(answerRecord1);

        List<BoAnswerItem> boAnswerItemList = boAnswerRecord.getBoAnswerItemList();
        List<AnswerItemContent> answerItemContentList = null;
        for(BoAnswerItem boAnswerItem:boAnswerItemList){
            List<BoAnswerItemContent> boAnswerItemContentList = boAnswerItem.getBoAnswerItemContentList();
            answerItemContentList = BeanUtil.copyToList(boAnswerItemContentList, AnswerItemContent.class);
        }

        List<AnswerItem> answerItemList = BeanUtil.copyToList(boAnswerItemList, AnswerItem.class);
        for(AnswerItem answerItem:answerItemList){
            AnswerItem answerItem1 = new AnswerItem();
            answerItem1.setAnswerRecordId(answerRecord2.getId());
            answerItem1.setQuestionItemId(answerItem.getQuestionItemId());
            AnswerItem answerItem2 = answerItemDao.save(answerItem1);

            if(answerItemContentList != null){
                for(AnswerItemContent answerItemContent:answerItemContentList){
                    AnswerItemContent answerItemContent1 = new AnswerItemContent();
                    answerItemContent1.setAnswerItemId(answerItem2.getId());
                    answerItemContent1.setQuestionItemContentId(answerItemContent.getQuestionItemContentId());
                    answerItemContentDao.save(answerItemContent1);
                }
            }else {
                throw new Exception("选项不存在");
            }
        }

        BoAnswerRecord boAnswerRecord1 = BeanUtil.copyProperties(answerRecord2, BoAnswerRecord.class);
        return boAnswerRecord1;
    }

    @Override
    public List<BoAnswerRecord> getBoAnswerRecordByRespondentId(Integer respondentId, Pageable pageable) {
        Page<AnswerRecord> answerRecordPage = answerRecordDao.
                findByRespondentIdOrderByAnswerTimeDesc(respondentId, pageable);
        List<BoAnswerRecord> boAnswerRecordList = new ArrayList<>();
        for(AnswerRecord answerRecord:answerRecordPage){
            BoAnswerRecord boAnswerRecord = BeanUtil.copyProperties(answerRecord, BoAnswerRecord.class);
            boAnswerRecordList.add(boAnswerRecord);
        }
        return boAnswerRecordList;
    }

    @Override
    public List<BoAnswerRecord> getBoAnswerRecordByQuestionnaireId(Integer questionnaireId, Pageable pageable) {
        Page<AnswerRecord> answerRecordPage = answerRecordDao.
                findByQuestionnaireIdOrderByAnswerTimeDesc(questionnaireId, pageable);
        List<BoAnswerRecord> boAnswerRecordList = new ArrayList<>();
        for(AnswerRecord answerRecord:answerRecordPage){
            BoAnswerRecord boAnswerRecord = BeanUtil.copyProperties(answerRecord, BoAnswerRecord.class);
            boAnswerRecordList.add(boAnswerRecord);
        }
        return boAnswerRecordList;
    }

    @Override
    public List<BoAnswerItem> getBoAnswerItemByAnswerRecordId(Integer boAnswerRecordId) {
        List<AnswerItem> answerItemList = answerItemDao.findByAnswerRecordId(boAnswerRecordId);
        List<BoAnswerItem> boAnswerItemList = BeanUtil.copyToList(answerItemList, BoAnswerItem.class);
        return boAnswerItemList;
    }

    @Override
    public List<BoAnswerItemContent> getBoAnswerItemContentByBoAnswerItemId(Integer boAnswerItemId) {
        List<AnswerItemContent> answerItemContentList = answerItemContentDao.findByAnswerItemId(boAnswerItemId);
        List<BoAnswerItemContent> boAnswerItemContentList = BeanUtil.copyToList(answerItemContentList,
                BoAnswerItemContent.class);
        return boAnswerItemContentList;
    }
}
