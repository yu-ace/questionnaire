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
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BoAnswerRecordService implements IBoAnswerRecordService {

    @Autowired
    IAnswerRecordDao answerRecordDao;
    @Autowired
    IAnswerItemDao answerItemDao;
    @Autowired
    IAnswerItemContentDao answerItemContentDao;


    /**
     * {
     * 	"respondentId": 5,
     * 	"questionnaireId": 1,
     * 	"boAnswerItemList": [{
     * 		"questionItemId": "1",
     * 		"boAnswerItemContentList": [{
     * 				"answerItemContent": "男"
     *                        }
     * 		]
     * 	},{
     * 		"questionItemId": "2",
     * 		"boAnswerItemContentList": [{
     * 				"answerItemContent": "喜欢"
     *            }
     * 		]
     * 	},{
     * 		"questionItemId": "3",
     * 		"boAnswerItemContentList": [{
     * 				"answerItemContent": "q"
     *            },
     *            {
     * 				"answerItemContent": "r"
     *            }
     * 		]
     * 	}]
     * }
     */



    @Override
    public BoAnswerRecord createBoAnswerRecord(BoAnswerRecord boAnswerRecord){
        AnswerRecord answerRecord = BeanUtil.copyProperties(boAnswerRecord, AnswerRecord.class);

        AnswerRecord answerRecord1 = new AnswerRecord();
        answerRecord1.setRespondentId(answerRecord.getRespondentId());
        answerRecord1.setQuestionnaireId(answerRecord.getQuestionnaireId());
        answerRecord1.setAnswerTime(new Date());
        answerRecordDao.save(answerRecord1);

        List<BoAnswerItem> boAnswerItemList = boAnswerRecord.getBoAnswerItemList();
        List<List<AnswerItemContent>> answerItemContentList = new ArrayList<>();
        for(BoAnswerItem boAnswerItem:boAnswerItemList){
            List<BoAnswerItemContent> boAnswerItemContentList = boAnswerItem.getBoAnswerItemContentList();
            List<AnswerItemContent> answerItemContentList1 = BeanUtil.copyToList(boAnswerItemContentList,
                    AnswerItemContent.class);
            answerItemContentList.add(answerItemContentList1);
        }

        List<AnswerItem> answerItemList = BeanUtil.copyToList(boAnswerItemList, AnswerItem.class);
        for(int i = 0;i < answerItemList.size();i++){
            AnswerItem answerItem1 = new AnswerItem();
            answerItem1.setAnswerRecordId(answerRecord1.getId());
            answerItem1.setQuestionItemId(answerItemList.get(i).getQuestionItemId());
            answerItemDao.save(answerItem1);

            for(AnswerItemContent answerItemContent:answerItemContentList.get(i)){
                answerItemContent.setAnswerItemId(answerItem1.getId());
                answerItemContent.setAnswerItemContent(answerItemContent.getAnswerItemContent());
                answerItemContentDao.save(answerItemContent);
            }

        }

        BoAnswerRecord boAnswerRecord1 = BeanUtil.copyProperties(answerRecord1, BoAnswerRecord.class);
        return boAnswerRecord1;
    }

    @Override
    public BoAnswerRecord getBoAnswerRecord(Integer id) {
        AnswerRecord answerRecord = answerRecordDao.getReferenceById(id);
        BoAnswerRecord boAnswerRecord = BeanUtil.copyProperties(answerRecord, BoAnswerRecord.class);

        List<AnswerItem> answerItemList = answerItemDao.findByAnswerRecordId(boAnswerRecord.getId());
        List<BoAnswerItem> boAnswerItemList = BeanUtil.copyToList(answerItemList, BoAnswerItem.class);

        for(int i = 0;i < boAnswerItemList.size();i++){
            List<AnswerItemContent> answerItemContentList = answerItemContentDao.
                    findByAnswerItemId(boAnswerItemList.get(i).getId());
            List<BoAnswerItemContent> boAnswerItemContentList = BeanUtil.
                    copyToList(answerItemContentList, BoAnswerItemContent.class);
            boAnswerItemList.get(i).setBoAnswerItemContentList(boAnswerItemContentList);
        }
        boAnswerRecord.setBoAnswerItemList(boAnswerItemList);
        return boAnswerRecord;
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

}
