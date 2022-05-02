package com.hospital_ssm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital_ssm.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorDao extends BaseMapper<Doctor> {
}
