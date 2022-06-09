package com.hospital_ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hospital_ssm.dao.RecodeDao;
import com.hospital_ssm.pojo.NumSource;
import com.hospital_ssm.pojo.Recode;
import com.hospital_ssm.pojo.WorkDay;
import com.hospital_ssm.service.RecodeService;
import com.hospital_ssm.util.Util;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = {"recode"})
public class RecodeServiceImpl implements RecodeService {
    @Autowired
    RecodeDao recodeDao;

    @Override
    public Recode getRecodeByNumSource(NumSource numSource) {
        QueryWrapper<Recode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wid", numSource.getState())
                .eq("visitdate", numSource.getVisitdate())
                .eq("visitnoon", numSource.getVisitnoon())
                .eq("visittime", numSource.getVisittime())
                .eq("state", "成功");
        return recodeDao.selectOne(queryWrapper);
    }

    /**
     * 发生改动时，简单粗暴清除该名称空间下所有缓存
     * @param recode
     * @return
     */
    @Override
    @CacheEvict(allEntries = true)
    public Integer insertRecode(Recode recode) {
        return recodeDao.insert(recode);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Integer cancelRecode(String rid) {
        UpdateWrapper<Recode> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("rid", rid)
                .set("state", "取消");
        return recodeDao.update(null, updateWrapper);
    }

    @Override
    public List<Recode> getSuccessRecodes(String wid, String date, String ampm) {
        QueryWrapper<Recode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wid", wid)
                .eq("visitdate", date)
                .eq("visitnoon", ampm)
                .eq("state", "成功");
        return recodeDao.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, String>> getRecodeByPatientID(String id) {
        return recodeDao.selectRecodeByPatientID(id);
    }

    @Override
    @Cacheable
    public List<Recode> getRecodeByWordDayAndVisitnoon(WorkDay workDay) {
        QueryWrapper<Recode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wid", workDay.getWid())
                .eq("visitnoon", workDay.getAmpm())
                .eq("visitdate", Util.getDate(workDay.getWorktime()))
                .eq("state", "成功");
        return recodeDao.selectList(queryWrapper);
    }
}
