package com.hospital_ssm.controller;


import com.hospital_ssm.pojo.Patient;
import com.hospital_ssm.pojo.Register;
import com.hospital_ssm.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RegisterController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    PatientService patientService;

    @RequestMapping("/register")
    public String register(Model model) {
        return "register";

    }

    @RequestMapping("/doRegister")
    public String doRegister(Register register, HttpSession session) {

        String message = "";
        //System.out.println(register);
        String password = register.getPassword();
        String passwordCof = register.getPasswordCof();
        if (password != null && password.equals(passwordCof)) {
            String account = register.getAccount();
            if (account != null) {
                List<Patient> patients = null;
                patients = patientService.getPatientByAccount(account);
//                System.out.println(patients);
                if (patients.size() == 0) {
                    String name = register.getName();
                    String email = register.getEmail();
                    Patient patient = new Patient(null, account, email, password, name);
                    if (patientService.insertPatient(patient) == 1) {
                        logger.info("注册成功");
                        message = "注册成功，请登录！";
                        session.setAttribute("message", message);
                        return "redirect:login";

                    } else {
                        logger.info("注册失败，sql改动数为0");
                        message = "注册失败，请稍后再试！";
                    }

                } else {
                    message = "该账号已存在！";
                }
            }else {
                message = "请输入账号！";
            }

        }else {
            message = "两次密码不正确！";
        }
        session.setAttribute("message", message);
        return "register";
    }
}
