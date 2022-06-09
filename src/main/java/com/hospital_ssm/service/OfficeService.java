package com.hospital_ssm.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Office;

public interface OfficeService {
    Page<Office> getOfficesByName(Page<Office> productPage, String name);

    Office getOfficeByName(String name);
}
