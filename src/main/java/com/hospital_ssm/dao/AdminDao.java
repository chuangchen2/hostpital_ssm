package com.hospital_ssm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital_ssm.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao extends BaseMapper<Admin> {
    @Select("SELECT aid, account, password, name FROM admin WHERE account=#{account}")
    Admin selectByAccount(@Param("account") String account);
}
