package com.hospital_ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkDay {
    @TableId(type = IdType.AUTO)
    private Integer wid;
    private Integer did;
    private Integer worktime;
    private String ampm;
    private Integer nsnum;
    private String state;
}
