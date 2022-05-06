package com.hospital_ssm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Doctor;

public interface DoctorService {
    Page<Doctor> getDoctors(Page<Doctor> productPage, String sort, Doctor condition);

    Integer insertDoctor(Doctor doctor);

    Integer updateDoctor(Doctor doctor);

    Integer removeDoctor(Doctor doctor);
}
