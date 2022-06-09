package com.hospital_ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class IndexController {

    @RequestMapping("help")
    public String help(){
        return "help";
    }

    @RequestMapping({"/", "index"})
    public String index(HttpSession session){
        // System.out.println(session.getAttribute("patient"));
        return "index";
    }
}
