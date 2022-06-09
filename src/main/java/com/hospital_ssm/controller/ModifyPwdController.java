package com.hospital_ssm.controller;

import com.hospital_ssm.pojo.Patient;
import com.hospital_ssm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ModifyPwdController {

    @Autowired
    PatientService patientService;

    @RequestMapping("modifyPwd")
    public String modifyPwd(){
        return "modifyPwd";
    }
    @RequestMapping("doModifyPwd")
    public String doModifyPwd(HttpSession session,String jmm, String xmm, String qdmm){
        String message="";
        Patient patient = new Patient();
        patient = (Patient) session.getAttribute("patient");
        if(patient.getPassword().equals(jmm)){
            if(xmm.equals(qdmm)){
                int k=patientService.updatePasswordByaccount(qdmm,patient.getAccount());
                if(k>0){
                    message = "修改成功";
                }else {
                    message = "修改失败";
                }
            }else {
                message = "两次密码不一样";
            }
        }else {
            message = "密码错误";
        }
        session.setAttribute("message",message);
        patient.setPassword(qdmm);
        session.setAttribute("patient",patient);
        return "modifyPwd";
    }

}