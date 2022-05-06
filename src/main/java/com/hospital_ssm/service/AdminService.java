package com.hospital_ssm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Admin;

public interface AdminService {

    Integer removeAdmin(Admin admin);

    Page<Admin> getAdmins(Page<Admin> producePage);

    /**
     * 通过ID获取管理员
     * @param id
     * @return
     */
     Admin getAdminById(String id);

     Integer insertAdmin(Admin admin);

     Integer updateAdmin(Admin admin);

     Admin getAdminByAccount(String account);

     String login(Admin admin) throws Exception;

     Admin getAdminByToken(String token) throws Exception;
}
