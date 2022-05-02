package com.hospital_ssm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Admin;
import com.hospital_ssm.service.AdminService;
import com.hospital_ssm.util.HttpCodeUtil;
import com.hospital_ssm.util.ListQuery;
import com.hospital_ssm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminInfo")
public class AdminInfoController {
    @Autowired
    AdminService adminService;

    @GetMapping("/list")
    public R fetchList(@RequestParam Map<String, String> listQuery) {
        // System.out.println(listQuery);
        Page<Admin> page = new Page<>(Integer.valueOf(listQuery.get("page")), Integer.valueOf(listQuery.get("limit")));
        Page<Admin> admins = adminService.getAdmins(page);
        R r = new R();
        HashMap<String, Object> data = new HashMap<>();
        data.put("total", admins.getTotal());
        data.put("items", admins.getRecords());
        r.setData(data);
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        return r;
    }

    @PostMapping("/create")
    public R createAdmin(@RequestBody Admin admin) {
        adminService.insertAdmin(admin);
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        r.setData("添加成功！");
        return r;
    }

    @PostMapping("/update")
    public R updateAdmin(Admin admin) {
        adminService.updateAdmin(admin);
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        r.setData("修改成功！");
        return r;
    }

    @PostMapping("/remove")
    public R removeAdmin(@RequestBody Admin admin) {
        if (adminService.removeAdmin(admin) != 0) {
            R r = new R();
            r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
            r.setData("删除成功！");
            return r;
        } else {
            R r = new R();
            r.setCode(Integer.valueOf(HttpCodeUtil.EXCEPTION.toString()));
            r.setData("删除失败！");
            return r;
        }
    }
}
