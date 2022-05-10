package com.hospital_ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hospital_ssm.dao.RecodeDao;
import com.hospital_ssm.pojo.NumSource;
import com.hospital_ssm.pojo.Recode;
import com.hospital_ssm.service.RecodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public Integer insertRecode(Recode recode) {
        return recodeDao.insert(recode);
    }

    @Override
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
}
