package com.example.questionnaire.controller;

import com.example.questionnaire.bo.BoAnswerRecord;
import com.example.questionnaire.bo.BoQuestionnaire;
import com.example.questionnaire.model.User;
import com.example.questionnaire.service.IBoAnswerRecordService;
import com.example.questionnaire.service.IBoQuestionnaireService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    IBoQuestionnaireService boQuestionnaireService;
    @Autowired
    IBoAnswerRecordService boAnswerRecordService;

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/questionnaire/dashBoard",method = RequestMethod.GET)
    public String dashBoard(){
        return "questionnaire/dashBoard";
    }

    @RequestMapping(path = "/questionnaire/create",method = RequestMethod.GET)
    public String creat(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "questionnaire/create";
    }

    @RequestMapping(path = "/questionnaire/join",method = RequestMethod.GET)
    public String join(Integer questionnaireId,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        BoQuestionnaire boQuestionnaire = boQuestionnaireService.getBoQuestionnaire(questionnaireId);
        model.addAttribute("user",user);
        model.addAttribute("boQuestionnaire",boQuestionnaire);
        return "questionnaire/join";
    }

    @RequestMapping(path = "/questionnaire/created",method = RequestMethod.GET)
    public String created(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageRequest of = PageRequest.of(0, 10);
        List<BoQuestionnaire> boQuestionnaireList = boQuestionnaireService.
                getBoQuestionnaireListByCreatorId(user.getId(), of);
        model.addAttribute("boQuestionnaireList",boQuestionnaireList);
        return "questionnaire/created";
    }

    @RequestMapping(path = "/questionnaire/page",method = RequestMethod.GET)
    public String questionnaireByPage(
            @RequestParam(name = "pageNumber")
            Integer pageNumber,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageRequest of = PageRequest.of(pageNumber, 10);
        List<BoQuestionnaire> boQuestionnaireList = boQuestionnaireService.
                getBoQuestionnaireListByCreatorId(user.getId(), of);
        model.addAttribute("boQuestionnaireList",boQuestionnaireList);
        return "questionnaire/created";
    }

    @RequestMapping(path = "/answerRecord/get",method = RequestMethod.GET)
    public String answerRecord(int questionnaireId,Model model){
        PageRequest of = PageRequest.of(0, 10);
        List<BoAnswerRecord> boAnswerRecordList = boAnswerRecordService.
                getBoAnswerRecordByQuestionnaireId(questionnaireId, of);
        model.addAttribute("questionnaireId",questionnaireId);
        model.addAttribute("boAnswerRecordList",boAnswerRecordList);
        return "questionnaire/participants";
    }

    @RequestMapping(path = "/answerRecord/page",method = RequestMethod.GET)
    public String answerItemByPage(
            @RequestParam(name = "pageNumber")
            Integer pageNumber,
            @RequestParam(name = "questionnaireId")
            Integer questionnaireId,
            Model model){
        PageRequest of = PageRequest.of(pageNumber, 10);
        List<BoAnswerRecord> boAnswerRecordList = boAnswerRecordService.
                getBoAnswerRecordByQuestionnaireId(questionnaireId, of);
        model.addAttribute("questionnaireId",questionnaireId);
        model.addAttribute("boAnswerRecordList",boAnswerRecordList);
        return "questionnaire/participants";
    }


    @RequestMapping(path = "/answerRecord/lookFor",method = RequestMethod.GET)
    public String lookFor(Integer answerRecordId,Model model){
        BoAnswerRecord boAnswerRecord = boAnswerRecordService.getBoAnswerRecord(answerRecordId);
        model.addAttribute("boAnswerRecord",boAnswerRecord);
        return "questionnaire/result";
    }
}
