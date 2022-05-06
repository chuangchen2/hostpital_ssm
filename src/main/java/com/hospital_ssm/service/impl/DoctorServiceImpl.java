package com.hospital_ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.dao.DoctorDao;
import com.hospital_ssm.pojo.Doctor;
import com.hospital_ssm.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorDao doctorDao;

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
        return doctorDao.insert(doctor);
    }

    @Override
    public Integer updateDoctor(Doctor doctor) {
        return doctorDao.updateById(doctor);
    }

    @Override
    public  Integer removeDoctor(Doctor doctor) {
        return doctorDao.deleteById(doctor.getDid());
    }
}
