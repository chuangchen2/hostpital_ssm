package com.hospital_ssm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Apply;
import com.hospital_ssm.pojo.Doctor;
import com.hospital_ssm.pojo.Patient;
import com.hospital_ssm.service.ApplyService;
import com.hospital_ssm.service.DoctorService;
import com.hospital_ssm.util.HttpCodeUtil;
import com.hospital_ssm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/doctorInfo")
public class DoctorInfoController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    ApplyService applyService;

    @GetMapping("/list")
    public R fetchPatientList(@RequestParam Map<String, String> param) {
        Page<Doctor> page = new Page<>(Integer.valueOf(param.get("page")), Integer.valueOf(param.get("limit")));
        Doctor doctor = new Doctor();
        doctor.setDname(param.get("dname"));
        doctor.setOffice(param.get("office"));
        doctor.setCareer(param.get("career"));
        doctorService.getDoctors(page, param.get("sort"), doctor);
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        HashMap<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("items", page.getRecords());
        r.setData(data);
        return r;
    }

    @PostMapping("/create")
    public R createPatient(@RequestBody Doctor doctor) {
        doctorService.insertDoctor(doctor);
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        r.setData("添加成功！");
        return r;
    }

    @PostMapping("/update")
    public R updateAdmin(@RequestBody Doctor doctor) {
        doctorService.updateDoctor(doctor);
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        r.setData("修改成功！");
        return r;
    }

    @PostMapping("/remove")
    public R removeAdmin(@RequestBody Doctor doctor) {
        if (doctorService.removeDoctor(doctor) != 0) {
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

    @GetMapping("/apply")
    public R fetchAppliesList(@RequestParam Map<String, String> param) {
        Page<Apply> page = new Page<>(Integer.valueOf(param.get("page")), Integer.valueOf(param.get("limit")));
        Apply apply = new Apply();
        apply.setDname(param.get("dname"));
        apply.setOffice(param.get("office"));
        applyService.getApplies(page, param.get("sort"), apply);
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        HashMap<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("items", page.getRecords());
        r.setData(data);
        return r;
    }

    @PostMapping("/apply")
    public R handleApply(@RequestParam String aid, @RequestParam Boolean condition) {
        if (applyService.handleApply(aid, condition) != 0) {
            R r = new R();
            r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
            r.setData("操作成功！");
            return r;
        } else {
            R r = new R();
            r.setCode(Integer.valueOf(HttpCodeUtil.EXCEPTION.toString()));
            r.setData("操作失败！");
            return r;
        }
    }

    @PutMapping("/apply")
    public R changeState(@RequestParam String aid) {
        if (applyService.changeState(aid) != 0) {
            R r = new R();
            r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
            r.setData("操作成功！");
            return r;
        } else {
            R r = new R();
            r.setCode(Integer.valueOf(HttpCodeUtil.EXCEPTION.toString()));
            r.setData("操作失败！");
            return r;
        }
    }
}
