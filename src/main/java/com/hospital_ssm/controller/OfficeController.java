package com.hospital_ssm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Office;
import com.hospital_ssm.service.OfficeService;
import com.hospital_ssm.util.Pages;
import com.hospital_ssm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @RequestMapping("/searchOffice")
    public ModelAndView searchOffice(HttpServletRequest req) {
        String name = Util.nullToString(req.getParameter("office"));
        int start = Util.nullToZero(req.getParameter("start"));

        Page offices = officeService.getOfficesByName(new Page<Office>(start, 10), name);
        Pages p = new Pages(start, (int) offices.getTotal(), 10);
        String limit = "limit " + ((p.getCurrentPage() - 1) * 10) + ",10";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderOffice");
        modelAndView.addObject("offices", offices.getRecords());
        modelAndView.addObject("office", name);
        modelAndView.addObject("pages", p);
        return modelAndView;
    }
}
