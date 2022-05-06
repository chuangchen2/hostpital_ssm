package com.hospital_ssm.controller;

import com.hospital_ssm.pojo.Admin;
import com.hospital_ssm.service.AdminService;
import com.hospital_ssm.util.HttpCodeUtil;
import com.hospital_ssm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/admin")
//@CrossOrigin(origins = {"http://127.0.0.1:9527", "http://localhost:9527"}, methods = {GET, POST, PUT, DELETE}, maxAge = 60L)
public class AdminLoginController {
    @Autowired
    private AdminService adminService;

    /**
     * 登录
     * @param request
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public R doLogin(HttpServletRequest request, @RequestBody Map<String, String> map) throws Exception {
        R r = new R();
        //System.out.println(username + "----------------" + password);
        Admin admin = new Admin();
        admin.setAccount(map.get("username"));
        admin.setPassword(map.get("password"));
        String token = adminService.login(admin);
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        r.setData(data);
        r.setMessage("登录成功");
        return r;
    }

    /**
     * 注销为空实现，直接返回成功
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public R logout() {
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        r.setMessage("注销成功");
        return r;
    }

    /**
     * 未登录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public R unauth() throws Exception {
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.UNAUTH.toString()));
        r.setMessage("重新登录");
        return r;
    }

    @RequestMapping(value = "/info")
    @ResponseBody
    public R getInfo(String token) throws Exception {
        // System.out.println("getInfo被调用");
        Admin admin = adminService.getAdminByToken(token);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("roles", Arrays.asList(admin.getAccount()));
        hashMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        hashMap.put("name", admin.getName());
        hashMap.put("introduction", "I am a super administrator");
        R r = new R();
        r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
        r.setData(hashMap);
        r.setMessage("获取用户信息成功！");
        return r;
    }
}
