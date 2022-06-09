package com.hospital_ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hospital_ssm.dao.WorkDayDao;
import com.hospital_ssm.pojo.WorkDay;
import com.hospital_ssm.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = {"workDay"})
public class WorkDayServiceImpl implements WorkDayService {
    @Autowired
    WorkDayDao workDayDao;

    @Override
    public WorkDay getWorkDay(String wid) {
        return workDayDao.selectById(wid);
    }

    @Override
    @Cacheable
    public List<WorkDay> getWorkDays(String did) {
        QueryWrapper<WorkDay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("did", did);
        return workDayDao.selectList(queryWrapper);
    }

    @Override
    public Integer updateWorkDay(WorkDay workDay) {
        return workDayDao.updateById(workDay);
    }

    @Override
    public Integer insertWorkDay(WorkDay workDay) {
        return workDayDao.insert(workDay);
    }

    @Override
    public Integer removeWorkDay(WorkDay workDay) {
        return workDayDao.deleteById(workDay.getWid());
    }
}
