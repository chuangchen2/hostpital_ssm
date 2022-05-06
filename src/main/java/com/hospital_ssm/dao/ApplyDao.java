package com.hospital_ssm.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ApplyDao extends BaseMapper<Apply> {
    @Select("<script>SELECT a.aid,w.wid,d.dname,d.office,a.reason,a.applytime,a.request,a.state,w.worktime,w.ampm " +
            "FROM `apply` a, doctor d, workday w " +
            "WHERE a.wid=w.wid AND w.did=d.did " +
            "<if test='ew.emptyOfWhere == false'>" +
            "AND ${ew.sqlSegment}" +
            "</if></script>")
    Page<Apply> selectPageWithDoctorInfo(Page<Apply> productPage, @Param(Constants.WRAPPER) QueryWrapper<Apply> queryWrapper);
}
