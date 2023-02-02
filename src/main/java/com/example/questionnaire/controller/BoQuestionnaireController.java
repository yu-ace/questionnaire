package com.example.questionnaire.controller;

import com.example.questionnaire.bo.BoQuestionItem;
import com.example.questionnaire.bo.BoQuestionnaire;
import com.example.questionnaire.service.IBoQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoQuestionnaireController {

    @Autowired
    IBoQuestionnaireService boQuestionnaireService;

    @ResponseBody
    @RequestMapping(path="/api/questionnaire/create",method = RequestMethod.PUT)
    public BoQuestionnaire creat(@RequestBody BoQuestionnaire boQuestionnaire){
        BoQuestionnaire boQuestionnaire1= boQuestionnaireService.createBoQuestionnaire(boQuestionnaire);
        return boQuestionnaire1;
    }

    @ResponseBody
    @RequestMapping(path="/api/questionItem",method = RequestMethod.GET)
    public BoQuestionItem questionName(Integer questionItemId){
        BoQuestionItem boQuestionItem = boQuestionnaireService.getBoQuestionItem(questionItemId);
        return boQuestionItem;
    }
}
