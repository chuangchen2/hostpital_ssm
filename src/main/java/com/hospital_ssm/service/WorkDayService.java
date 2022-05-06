package com.hospital_ssm.service;

import com.hospital_ssm.pojo.WorkDay;

import java.util.List;

public interface WorkDayService {
    WorkDay getWorkDay(String wid);

    List<WorkDay> getWorkDays(String did);

    Integer updateWorkDay(WorkDay workDay);

    Integer insertWorkDay(WorkDay workDay);

    Integer removeWorkDay(WorkDay workDay);
}
