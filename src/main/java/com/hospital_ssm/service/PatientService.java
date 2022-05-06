package com.hospital_ssm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Patient;

public interface PatientService {

    Page<Patient> getPatients(Page<Patient> productPage, String sort, String account);

    Integer insertPatient(Patient patient);

    Integer updatePatient(Patient patient);

    Integer removePatient(Patient patient);
}
