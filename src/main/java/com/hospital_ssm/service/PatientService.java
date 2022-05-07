package com.hospital_ssm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Patient;
import java.util.List;

public interface PatientService {

    Page<Patient> getPatients(Page<Patient> productPage, String sort, String account);

    Integer insertPatient(Patient patient);

    Integer updatePatient(Patient patient);

    Integer removePatient(Patient patient);

    //依据id的五个基本方法
	public int addPatient(Patient patient) ;
	public int deletePatient(Integer id);
	public Patient selectOne(Integer id);
	public List<Patient> findAll() ;
	
	//根据姓名查找患者的方法
	public Patient selectOneByName(String name);
	public String chcekpassword(String name,String password);
	
	//找回密码的认证主方法(findPwk)
	public Patient findPwdCheck(String name,String email);
	//根据id和键入的password 修改对应id患者的密码(findPwk)
	public int updatePatientPassword(String id,String password);
}
