package com.hospital_ssm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital_ssm.pojo.Recode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecodeDao extends BaseMapper<Recode> {
    @Select("select recode.rid,recode.pid,recode.wid,recode.did,recode.serialnumber," +
            "recode.visitdate,recode.visitnoon,recode.visittime,recode.ordertime,recode.state," +
            "doctor.dname,doctor.office,doctor.room,doctor.picpath,doctor.fee" +
            " from recode,doctor" +
            " where recode.pid=#{id}  and doctor.did=recode.did" +
            " order by recode.ordertime desc;")
    List<Map<String, String>> selectRecodeByPatientID(String id);
}
