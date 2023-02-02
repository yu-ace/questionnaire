package com.example.questionnaire.controller;

import com.example.questionnaire.model.User;
import com.example.questionnaire.service.IVerificationCodeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerificationCodeController {

    @Autowired
    IVerificationCodeService verificationCodeService;

    @ResponseBody
    @RequestMapping(path = "/api/verificationCode/send",method = RequestMethod.GET)
    public void send(String cellphone){
        verificationCodeService.sendVerificationCode(cellphone);
    }

    @RequestMapping(path = "/verificationCode/check",method = RequestMethod.POST)
    public String checkVerificationCode(
            @RequestParam(name = "cellphone")
            String cellphone,
            @RequestParam(name = "code")
            String code, Model model, HttpServletRequest request){
        try {
            User user = verificationCodeService.checkVerificationCode(cellphone, code);
            request.getSession().setAttribute("user",user);
            return "redirect:/questionnaire/dashBoard";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "login";
        }
    }
}
