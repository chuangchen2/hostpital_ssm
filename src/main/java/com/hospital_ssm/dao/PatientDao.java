package com.hospital_ssm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital_ssm.pojo.Patient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientDao extends BaseMapper<Patient> {
}
