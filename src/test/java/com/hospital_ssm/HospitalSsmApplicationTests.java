package com.hospital_ssm;

import com.hospital_ssm.dao.AdminDao;
import com.hospital_ssm.pojo.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HospitalSsmApplicationTests {
    @Autowired
    private AdminDao adminDao;

    @Test
    void contextLoads() {
//        List<Admin> admins = adminDao.selectList(null);
//        for (Admin admin : admins) {
//            System.out.println(admin);
//        }
        //defaultWebSecurityManager.getSessionMode();
    }

}
