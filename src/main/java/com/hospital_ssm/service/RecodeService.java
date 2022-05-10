package com.hospital_ssm.service;

import com.hospital_ssm.pojo.NumSource;
import com.hospital_ssm.pojo.Recode;

import java.util.List;

public interface RecodeService {
    Recode getRecodeByNumSource(NumSource numSource);

    Integer insertRecode(Recode recode);

    Integer cancelRecode(String rid);

    List<Recode> getSuccessRecodes(String wid, String date, String ampm);
}
