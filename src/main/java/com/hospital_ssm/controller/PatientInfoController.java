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

@RestController
@RequestMapping("/patientInfo")
public class PatientInfoController {
    @Autowired
    PatientService patientService;

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
