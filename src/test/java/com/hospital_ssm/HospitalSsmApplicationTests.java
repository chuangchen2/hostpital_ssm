package com.hospital_ssm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.dao.AdminDao;
import com.hospital_ssm.dao.ApplyDao;
import com.hospital_ssm.pojo.Admin;
import com.hospital_ssm.pojo.Apply;
import com.hospital_ssm.pojo.Patient;
import com.hospital_ssm.service.AdminService;
import com.hospital_ssm.service.ApplyService;
import com.hospital_ssm.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HospitalSsmApplicationTests {
    @Autowired
    AdminService adminService;
    @Autowired
    ApplyDao applyDao;
    @Autowired
    ApplyService applyService;

    @Test
    void contextLoads() {
        QueryWrapper<Apply> wrapper = new QueryWrapper<>();
        wrapper.orderBy(true, true, "aid")
            .eq("a.state", "取消");
        Page<Apply> applyPage = applyDao.selectPageWithDoctorInfo(new Page<>(1, 5), wrapper);
        List<Apply> records = applyPage.getRecords();
        System.out.println(records);
    }

    @Test
    void testApplyService() {
        Apply apply = new Apply();
        apply.setDname("");
        apply.setOffice("");
        Page<Apply> applies = applyService.getApplies(new Page<>(1, 5), "+id", apply);
        System.out.println(applies.getRecords());
    }


}
