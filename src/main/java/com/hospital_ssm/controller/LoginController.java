package com.hospital_ssm.controller;

import com.hospital_ssm.pojo.Patient;
import com.hospital_ssm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
//@SessionAttributes("patient")
public class LoginController {

    @Autowired
    PatientService patientService;

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus) {//登出，删除sessi中的patient
        session.removeAttribute("patient");
        sessionStatus.setComplete();
        return "redirect:/login";
    }

    @RequestMapping("doLogin")
    public  String doLogin(String password, String account, HttpSession session,SessionStatus sessionStatus){
        String message="";
        List<Patient> patients = patientService.getPatientByAccount(account);
        if(patients.size()>0){
            Patient patient=patients.get(0);
            if(password.equals(patient.getPassword())){
                session.setAttribute("patient",patient);
                session.removeAttribute("message");
                String url = (String) session.getAttribute("url");
                if (url == null) {
                    return "redirect:index";
                } else {
                    return "redirect:" + url;
                }

            }

        }
        session.setAttribute("message","用户名或者密码错误！");
        return "login";
    }
}
