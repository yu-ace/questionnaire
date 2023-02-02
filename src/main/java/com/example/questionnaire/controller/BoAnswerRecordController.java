package com.example.questionnaire.controller;

import com.example.questionnaire.bo.BoAnswerRecord;
import com.example.questionnaire.bo.BoQuestionnaire;
import com.example.questionnaire.service.IBoAnswerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoAnswerRecordController {

    @Autowired
    IBoAnswerRecordService boAnswerRecordService;

    @ResponseBody
    @RequestMapping(path="/api/answerRecord/create",method = RequestMethod.PUT)
    public BoAnswerRecord creat(@RequestBody BoAnswerRecord boAnswerRecord){
        BoAnswerRecord boAnswerRecord1 = boAnswerRecordService.createBoAnswerRecord(boAnswerRecord);
        return boAnswerRecord1;
    }
}
