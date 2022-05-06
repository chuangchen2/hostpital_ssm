package com.hospital_ssm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital_ssm.pojo.Apply;

public interface ApplyService {
    Page<Apply> getApplies(Page<Apply> productPage, String sort, Apply condition);

    Integer handleApply(String aid, Boolean condition);

    Integer changeState(String aid);
}
