package com.example.questionnaire.controller;

import com.example.questionnaire.bo.BoQuestionnaire;
import com.example.questionnaire.model.User;
import com.example.questionnaire.service.IBoQuestionnaireService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    IBoQuestionnaireService boQuestionnaireService;

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
        return "user/dashBoard";
    }

    @RequestMapping(path = "/questionnaire/create",method = RequestMethod.GET)
    public String creat(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "user/create";
    }

    @RequestMapping(path = "/questionnaire/join",method = RequestMethod.GET)
    public String join(Integer questionnaireId,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        BoQuestionnaire boQuestionnaire = boQuestionnaireService.getBoQuestionnaire(questionnaireId);
        model.addAttribute("user",user);
        model.addAttribute("boQuestionnaire",boQuestionnaire);
        return "user/join";
    }
}
