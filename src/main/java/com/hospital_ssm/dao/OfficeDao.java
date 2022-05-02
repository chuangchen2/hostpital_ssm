package com.hospital_ssm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital_ssm.pojo.Office;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OfficeDao extends BaseMapper<Office> {
}
