package com.hospital_ssm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital_ssm.pojo.Patient;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("patientDao")
public interface PatientDao extends BaseMapper<Patient> {
    @Insert("insert into patient(account,email,password,name) values (#{account},#{email},#{password},#{name})")
	public int addPatient(Patient patient);
	
	@Delete("delete from patient where id = #{id}")
	public int deletePatient();
	
	@Update("update patient set account = #{account},email=#{email},password=#{password},name=#{name}")
	public int updatePatient(Patient patient);
	
	
	@Select("select * from patient where id = #{id}")
	@ResultType(Patient.class)
	public Patient selectOne(Integer id);
	
	@Select("select * from patient where name = #{name}")
	@ResultType(Patient.class)
	public Patient selectOneByName(String name);
	
	@Select("select * from patient")
	@ResultType(Patient.class)
	public List<Patient> findAll();
	
	//根据输入的名字和密码 在数据库中寻找是否存在这个密码，并且返回密码(用于验证管理员登录)
	@Select("select password from patient where name=#{name} and password=#{password}")
	@ResultType(String.class)
	//public String chcekpassword(String name,String password);
	public String chcekpassword(@Param("name")String name,@Param("password")String password);
	
	//根据名字和邮箱 在数据库中寻找是否存在这样的用户，找到该用户并返回，方便后续修改密码(用于找回密码findPwd)
	@Select("select * from patient where name=#{name} and email=#{email}")
	public Patient findPwdCheck(@Param("name")String name,@Param("email")String email);
	
	//根据id和键入的password 修改对应id患者的密码(findPwk)
	@Update("update patient set password=#{password} where id=#{id}")
	public int updatePatientPassword(@Param("id")String id,@Param("password")String password);
}
