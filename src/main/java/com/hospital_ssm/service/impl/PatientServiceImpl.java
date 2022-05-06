package com.hospital_ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.dao.PatientDao;
import com.hospital_ssm.pojo.Patient;
import com.hospital_ssm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientDao patientDao;

    @Override
    public Page<Patient> getPatients(Page<Patient> productPage, String sort, String account) {
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!account.equals(""), "account", account)
                .orderBy(!sort.equals(""), sort.equals("+id"), "pid");
        return patientDao.selectPage(productPage, queryWrapper);
    }

    @Override
    public Integer insertPatient(Patient patient) {
        return patientDao.insert(patient);
    }

    @Override
    public Integer updatePatient(Patient patient) {
        return patientDao.updateById(patient);
    }

    @Override
    public Integer removePatient(Patient patient) {
        return patientDao.deleteById(patient.getPid());
    }
}
