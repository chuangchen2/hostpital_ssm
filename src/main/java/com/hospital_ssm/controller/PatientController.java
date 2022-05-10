package com.hospital_ssm.controller;

import com.hospital_ssm.pojo.*;
import com.hospital_ssm.service.DoctorService;
import com.hospital_ssm.service.RecodeService;
import com.hospital_ssm.service.WorkDayService;
import com.hospital_ssm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class PatientController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    WorkDayService workDayService;
    @Autowired
    RecodeService recodeService;


    @GetMapping("/showWeek")
    public ModelAndView showWorkDay(HttpServletRequest request, HttpServletResponse response) {
        String did = request.getParameter("did");
        if (did == null || did.equals("")) {
            new ModelAndView("forward:index");
        }
        Doctor doctor = doctorService.getDoctorByID(did);
        List<WorkDay> workDays = workDayService.getWorkDays(did);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("doctorInfo");
        modelAndView.addObject("workDays", workDays);
        modelAndView.addObject("doctor", doctor);
        return modelAndView;
    }

    @RequestMapping("/order")
    public ModelAndView order(HttpServletRequest request, HttpServletResponse response) {
        Patient patient = (Patient) request.getSession().getAttribute("patient");
        patient.setPid(1);
        String wid = request.getParameter("wid");
        String did = request.getParameter("did");
        String rid = request.getParameter("rid");
        String action = request.getParameter("action");
        String data = Util.nullToString(request.getParameter("data"));
        String[] strings = data.split(",");
        switch (action) {
            case "order" :
                NumSource numSource = new NumSource(strings[0], strings[1], strings[2], strings[3], wid);
                Doctor doctor = doctorService.getDoctorByID(did);
                request.getSession().setAttribute("numSource", numSource);
                request.setAttribute("doctor", doctor);
                return new ModelAndView("forward:/confirmOrder");
            case "confirm" :
                NumSource numSource1 = (NumSource) request.getSession().getAttribute("numSource");
                Recode recode = recodeService.getRecodeByNumSource(numSource1);
                if (recode == null) {
                    recode = new Recode();
                    recode.setPid(patient.getPid());
                    recode.setDid(Integer.valueOf(did));
                    recode.setOrdertime(new Timestamp(new Date().getTime()).toString());
                    recode.setSerialnumber(numSource1.getSerialnumber());
                    recode.setVisitdate(numSource1.getVisitdate());
                    recode.setVisitnoon(numSource1.getVisitnoon());
                    recode.setVisittime(numSource1.getVisittime());
                    recode.setState("成功");
                    if (recodeService.insertRecode(recode) != 0) {
                        request.getSession().setAttribute("message", "预约成功！");
                        return new ModelAndView("forward:/orderList");
                    } else {
                        request.getSession().setAttribute("message", "预约失败！");
                        return new ModelAndView("forward:/showWeek?did=" + did);
                    }
                } else {
                    request.getSession().setAttribute("message", "号源已被预约！");
                    return new ModelAndView("forward:/showWeek?did=" + did);
                }
            case "cancel" :
                recodeService.cancelRecode(rid);
                return new ModelAndView("forward:/orderList");
        }
        request.getSession().setAttribute("message", "操作失败！");
        return new ModelAndView("forward:/showWeek?did" + did);
    }

    @RequestMapping("/queryNumSource")
    @ResponseBody
    public ArrayList<HashMap<String, String>> queryNumSource(HttpServletRequest req, HttpServletResponse resp) {
        String wid = req.getParameter("wid");
        String ampm = req.getParameter("ampm");
        String date = req.getParameter("date");
        if (wid != null) {
            WorkDay workDay = workDayService.getWorkDay(wid);
            int nsnum = workDay.getNsnum();
            int size = 240/nsnum;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MINUTE, 30);
            if ("上午".equals(ampm)) {
                calendar.set(Calendar.HOUR_OF_DAY, 8);
            } else {
                calendar.set(Calendar.HOUR_OF_DAY, 13);
            }
            List<Recode> successRecodes = recodeService.getSuccessRecodes(wid, date, ampm);
            ArrayList<NumSource> numSources = new ArrayList<>();
            for (int i = 1, j = 0; i <= nsnum; i++) {
                String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
                NumSource numSource = new NumSource(i + "", date, ampm, time, "可预约");
                if (j < successRecodes.size()) {
                    if (successRecodes.get(j).getSerialnumber().equals(i + "")) {
                        numSource.setState("已被预约");
                        j++;
                    }
                }
                calendar.add(Calendar.MINUTE, size);
                numSources.add(numSource);
            }
            ArrayList<HashMap<String, String>> returnArray = new ArrayList<>();
            for (NumSource o : numSources) {
                HashMap<String, String> returnMap = new HashMap<>();
                returnMap.put("date", o.getVisitdate());
                returnMap.put("ampm", o.getVisitnoon());
                returnMap.put("serialnumber", o.getSerialnumber());
                returnMap.put("state", o.getState());
                returnMap.put("time", o.getVisittime());
                returnArray.add(returnMap);
            }
            return returnArray;
        }
        return null;
    }

    @RequestMapping("/confirmOrder")
    public ModelAndView confirmOrder() {
        return new ModelAndView("confirmOrder");
    }

    @RequestMapping("/orderList")
    public ModelAndView orderList() {
        return new ModelAndView("orderList");
    }
}
