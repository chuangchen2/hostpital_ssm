package com.hospital_ssm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Admin;
import com.hospital_ssm.pojo.Patient;
import com.hospital_ssm.service.PatientService;
import com.hospital_ssm.util.HttpCodeUtil;
import com.hospital_ssm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/patientInfo")
public class PatientInfoController {
    @Autowired
    @Qualifier(value="patientService")
    PatientService patientService;


    //from yjb 注意findPwd页面的 确认密码的name是否与函数中参数名称相同
    @RequestMapping(value="/findPwdCheck",method=RequestMethod.POST)
	public String findPwdCheck(String name,String email,String password,String confirmpassword,HttpServletRequest request) {
		//进行找回密码的信息认证，确定是否为本人
		if(name!=null&&email!=null) {
			Patient patient = patientService.findPwdCheck(name,email);
			if(patient!=null) {
				if(patient.getEmail().equals(email)&&password.equals(confirmpassword)) {
					patientService.updatePatientPassword(patient.getId(),password);
					request.getSession().setAttribute("findPwdSuccess", "密码修改成功！");
					return "login";
				}
			}
		}
		request.getSession().setAttribute("findPwdMsg", "信息认证输入有误或者新密码与确认密码不一致!");
		return "findPwd";
	}

    @GetMapping("/list")
    public R fetchPatientList(@RequestParam Map<String, String> param) {
        Page<Patient> page = new Page<>(Integer.valueOf(param.get("page")), Integer.valueOf(param.get("limit")));
        patientService.getPatients(page, param.get("sort"), param.get("account"));
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        HashMap<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("items", page.getRecords());
        r.setData(data);
        return r;
    }

    @PostMapping("/create")
    public R createPatient(@RequestBody Patient patient) {
        patientService.insertPatient(patient);
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        r.setData("添加成功！");
        return r;
    }

    @PostMapping("/update")
    public R updateAdmin(@RequestBody Patient patient) {
        patientService.updatePatient(patient);
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        r.setData("修改成功！");
        return r;
    }

    @PostMapping("/remove")
    public R removeAdmin(@RequestBody Patient patient) {
        if (patientService.removePatient(patient) != 0) {
            R r = new R();
            r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
            r.setData("删除成功！");
            return r;
        } else {
            R r = new R();
            r.setCode(Integer.valueOf(HttpCodeUtil.EXCEPTION.toString()));
            r.setData("删除失败！");
            return r;
        }
    }
}
