package com.hospital_ssm.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.dao.AdminDao;
import com.hospital_ssm.pojo.Admin;
import com.hospital_ssm.service.AdminService;
import com.hospital_ssm.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Integer removeAdmin(Admin admin) {
        return adminDao.deleteById(admin.getAid());
    }

    @Override
    public Page<Admin> getAdmins(Page<Admin> producePage) {
        return adminDao.selectPage(producePage, null);
    }

    @Override
    public Admin getAdminById(String id) {
        return adminDao.selectById(id);
    }

    @Override
    public Integer insertAdmin(Admin admin) {
        return adminDao.insert(admin);
    }

    @Override
    public Integer updateAdmin(Admin admin) {
        return adminDao.update(admin, null);
    }

    @Override
    public Admin getAdminByAccount(String account) {
        return adminDao.selectByAccount(account);
    }

    @Override
    public String login(Admin admin) throws Exception {
        if (admin.getAccount() == null || admin.getAccount().equals("")) {
            throw new Exception("账户名为空！");
        }
        Admin dbData = getAdminByAccount(admin.getAccount());
        if (!dbData.getPassword().equals(admin.getPassword())) {
            throw new Exception("登录密码错误! ");
        }
        String token = JWTUtil.createToken(dbData.getAid().toString());
        return token;
    }

    @Override
    public Admin getAdminByToken(String token) throws Exception {
        String aid = JWTUtil.validateToken(token);
        if (aid == null || aid.equals("")) {
            throw new Exception("令牌不存在，请重新登录！");
        }
        Admin admin = getAdminById(aid);
        return admin;
    }
}
