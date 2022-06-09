package com.hospital_ssm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.dao.OfficeDao;
import com.hospital_ssm.pojo.Doctor;
import com.hospital_ssm.pojo.Office;
import com.hospital_ssm.service.DoctorService;
import com.hospital_ssm.service.OfficeService;
import com.hospital_ssm.util.Pages;
import com.hospital_ssm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private OfficeService officeService;

    @RequestMapping("/searchDoctor")
    public ModelAndView searchDoctor(HttpServletRequest req) {
        String office = Util.nullToString(req.getParameter("office"));
        String name = Util.nullToString(req.getParameter("doctor"));
        int start = Util.nullToZero(req.getParameter("start"));

        Page<Doctor> page = doctorService.getDoctorByOfficeAndName(new Page<>(start, 6), office, name);
        Pages pages = new Pages(start, (int) page.getTotal(), 6);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("doctors", page.getRecords());
        modelAndView.addObject("pages", pages);
        modelAndView.addObject("doctor", name);
        if (!office.equals("")) {
            modelAndView.addObject("office", officeService.getOfficeByName(office));
        }
        String order = req.getParameter("order");
        if ("all".equals(order)) {
            modelAndView.setViewName("orderDoctor");
        } else {
            modelAndView.setViewName("officeInfo");
        }
        return modelAndView;
    }
}
