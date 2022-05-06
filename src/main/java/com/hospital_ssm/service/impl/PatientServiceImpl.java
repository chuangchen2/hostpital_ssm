package com.hospital_ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.dao.PatientDao;
import com.hospital_ssm.pojo.Patient;
import com.hospital_ssm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;

@Service("patientService")
@Transactional(propagation=Propagation.REQUIRED)
public class PatientServiceImpl implements PatientService {
    @Autowired
    @Qualifier(value="patientDao")
    PatientDao patientDao;

    @Override
    public Page<Patient> getPatients(Page<Patient> productPage, String sort, String account) {
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!account.equals(""), "account", account)
                .orderBy(!sort.equals(""), sort.equals("+id"), "pid");
        return patientDao.selectPage(productPage, queryWrapper);
    }

    @Override
    public Integer insertPatient(Patient patient) {
        return patientDao.insert(patient);
    }

    @Override
    public Integer updatePatient(Patient patient) {
        return patientDao.updateById(patient);
    }

    @Override
    public Integer removePatient(Patient patient) {
        return patientDao.deleteById(patient.getPid());
    }

    //from yjb
    //添加患者
		@Override
		public int addPatient(Patient patient) {
			return patientDao.addPatient(patient);
		} 
		
		
		//删除患者
		@Override
		public int deletePatient(Integer id) {
			return patientDao.deletePatient();
		}


		//更新患者
		@Override
		public int updatePatient(Patient patient) {
			return patientDao.updatePatient(patient);
		}


		//根据id查患者
		@Override
		public Patient selectOne(Integer id) {
			return patientDao.selectOne(id);
		}


		//根据名称查患者
		@Override
		public Patient selectOneByName(String name) {
			return patientDao.selectOneByName(name);
		}

		//查询所有患者
		@Transactional(propagation=Propagation.NOT_SUPPORTED)
		@Override
		public List<Patient> findAll() {
			return patientDao.findAll();
		}

		//检查密码合法性（by myself）
		public String chcekpassword(String name,String password) {
			return patientDao.chcekpassword(name, password);
		}
		
		//找回密码的认证主方法(findPwk)
		public Patient findPwdCheck(String name,String email) {
			return patientDao.findPwdCheck(name, email);
		}
		
		//根据id和键入的password 修改对应id患者的密码(findPwk)
		 public int updatePatientPassword(String id,String password) {
			  return patientDao.updatePatientPassword(id,password);
		 }


		public PatientDao getPatientDao() {
			return patientDao;
		}


		public void setPatientDao(PatientDao patientDao) {
			this.patientDao = patientDao;
		} 
}
