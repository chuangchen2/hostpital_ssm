package com.hospital_ssm.service;

import com.hospital_ssm.pojo.NumSource;
import com.hospital_ssm.pojo.Recode;
import com.hospital_ssm.pojo.WorkDay;

import java.util.List;
import java.util.Map;

public interface RecodeService {
    Recode getRecodeByNumSource(NumSource numSource);

    Integer insertRecode(Recode recode);

    Integer cancelRecode(String rid);

    List<Recode> getSuccessRecodes(String wid, String date, String ampm);

    List<Map<String, String>> getRecodeByPatientID(String id);

    List<Recode> getRecodeByWordDayAndVisitnoon(WorkDay workDay);
}
