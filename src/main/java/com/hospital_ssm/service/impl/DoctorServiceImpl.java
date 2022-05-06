package com.hospital_ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.dao.DoctorDao;
import com.hospital_ssm.dao.WorkDayDao;
import com.hospital_ssm.pojo.Doctor;
import com.hospital_ssm.pojo.WorkDay;
import com.hospital_ssm.service.DoctorService;
import com.hospital_ssm.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    WorkDayService workDayService;

    @Override
    public Page<Doctor> getDoctors(Page<Doctor> productPage, String sort, Doctor condition) {
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!condition.getDname().equals(""), "dname", condition.getDname())
                .like(!condition.getOffice().equals(""), "office", condition.getOffice())
                .like(!condition.getCareer().equals(""), "career", condition.getCareer())
                .orderBy(!sort.equals(""), sort.equals("+id"), "did");
        return doctorDao.selectPage(productPage, queryWrapper);
    }

    @Override
    public Integer insertDoctor(Doctor doctor) {
        if (doctor.getPicpath() == null || doctor.getPicpath().equals("")) {
            doctor.setPicpath("/images/docpic/default.jpg");
        }
        int ret = doctorDao.insert(doctor);
        WorkDay workDay = new WorkDay();
        workDay.setDid(doctor.getDid());
        workDay.setState("停诊");
        workDay.setNsnum(0);
        for (int i = 0; i < 7; i++) {
            workDay.setWorktime(i);
            workDay.setAmpm("上午");
            workDayService.insertWorkDay(workDay);
            workDay.setAmpm("下午");
            workDayService.insertWorkDay(workDay);
        }
        return ret;
    }

    @Override
    public Integer updateDoctor(Doctor doctor) {
        return doctorDao.updateById(doctor);
    }

    @Override
    public  Integer removeDoctor(Doctor doctor) {
        List<WorkDay> workDays = workDayService.getWorkDays(doctor.getDid().toString());
        for (WorkDay item : workDays) {
            workDayService.removeWorkDay(item);
        }
        return doctorDao.deleteById(doctor.getDid());
    }
}
