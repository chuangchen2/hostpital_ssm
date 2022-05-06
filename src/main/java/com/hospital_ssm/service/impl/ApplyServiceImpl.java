package com.hospital_ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.dao.ApplyDao;
import com.hospital_ssm.pojo.Apply;
import com.hospital_ssm.pojo.WorkDay;
import com.hospital_ssm.service.ApplyService;
import com.hospital_ssm.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    ApplyDao applyDao;
    @Autowired
    WorkDayService workDayService;

    @Override
    public Page<Apply> getApplies(Page<Apply> productPage, String sort, Apply condition) {
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!condition.getDname().equals(""), "dname", condition.getDname())
                .like(!condition.getOffice().equals(""), "office", condition.getOffice())
                .orderBy(!sort.equals(""), sort.equals("+id"), "aid");
        applyDao.selectPageWithDoctorInfo(productPage, queryWrapper);
        for (Apply item : productPage.getRecords()) {
            if (item.getAmpm().equals("上午")) {
                switch (item.getWorktime()) {
                    case "0" :
                        item.setWorktime("08:30 - 09:00");
                        break;
                    case "1" :
                        item.setWorktime("09:00 - 09:30");
                        break;
                    case "2" :
                        item.setWorktime("09:30 - 10:00");
                        break;
                    case "3" :
                        item.setWorktime("10:00 - 10:30");
                        break;
                    case "4" :
                        item.setWorktime("10:30 - 11:00");
                        break;
                    case "5" :
                        item.setWorktime("11:00 - 11:30");
                        break;
                    case "6" :
                        item.setWorktime("11:30 - 12:00");
                        break;
                }
            } else {
                switch (item.getWorktime()) {
                    case "0" :
                        item.setWorktime("02:00 - 02:30");
                        break;
                    case "1" :
                        item.setWorktime("02:30 - 03:00");
                        break;
                    case "2" :
                        item.setWorktime("03:00 - 03:30");
                        break;
                    case "3" :
                        item.setWorktime("03:30 - 4:00");
                        break;
                    case "4" :
                        item.setWorktime("04:00 - 04:30");
                        break;
                    case "5" :
                        item.setWorktime("04:30 - 05:00");
                        break;
                    case "6" :
                        item.setWorktime("05:00 - 05:30");
                        break;
                }
            }
        }
        return productPage;
    }

    @Override
    public Integer handleApply(String aid, Boolean condition) {
        Apply apply = applyDao.selectById(aid);
        if (condition) {
            if (apply.getRequest().equals("申请停诊")) {
                WorkDay workDay = workDayService.getWorkDay(apply.getWid().toString());
                workDay.setNsnum(0);
                workDay.setState("停诊");
                workDayService.updateWorkDay(workDay);
            } else {
                WorkDay workDay = workDayService.getWorkDay(apply.getWid().toString());
                workDay.setNsnum(10);
                workDay.setState("预约");
                workDayService.updateWorkDay(workDay);
            }
            apply.setState("同意");
        } else {
            apply.setState("取消");
        }
        return applyDao.updateById(apply);
    }

    @Override
    public Integer changeState(String aid) {
        Apply apply = applyDao.selectById(aid);
        if (apply.getState().equals("同意")) {
            apply.setState("取消");
        } else {
            apply.setState("同意");
        }
        return applyDao.updateById(apply);
    }
}
