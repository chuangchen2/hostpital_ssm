package com.hospital_ssm.controller;

import com.hospital_ssm.pojo.Patient;
import com.hospital_ssm.pojo.Register;
import com.hospital_ssm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FindPwdController {

    @Autowired
    PatientService patientService;

    @RequestMapping("findPwd")
    public String findPwd(){
        return "findPwd";
    }

    @RequestMapping("doFindPwd")
    public String doFindPwd(Register register,HttpSession session){
        String account = register.getAccount();
        String email = register.getEmail();
        List<Patient> list = patientService.getPatientByAccount(account);
        String message="";
        if(list.size()>0){
            if(list.get(0).getEmail().equals(email)){
                if(register.getPassword().equals(register.getPasswordCof())){
                    int k=patientService.updatePasswordByaccount(register.getPassword(), account);
                    if(k>0){
                        message = "密码修改成功，请登录！";
                        session.setAttribute("message", message);
                        return "redirect:login";
                    }
                    else {
                        message = "密码修改失败，请稍后再试！";
                    }
                }
            }else {
                message = "邮箱错误！";
            }
        }else{
            message = "账号不存在!";
        }
        session.setAttribute("message", message);
        return "findPwd";
    }
}
