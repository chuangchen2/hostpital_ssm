package com.hospital_ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.dao.OfficeDao;
import com.hospital_ssm.pojo.Office;
import com.hospital_ssm.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeDao officeDao;

    @Override
    public Page<Office> getOfficesByName(Page<Office> productPage, String name) {
        QueryWrapper<Office> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!"".equals(name), "officename", name);
        return officeDao.selectPage(productPage, queryWrapper);
    }

    @Override
    public Office getOfficeByName(String name) {
        QueryWrapper<Office> queryWrapper = new QueryWrapper<Office>()
                .eq(!"".equals(name), "officename", name);
        return officeDao.selectOne(queryWrapper);
    }
}
