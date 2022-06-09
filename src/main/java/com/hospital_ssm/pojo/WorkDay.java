package com.hospital_ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("workday")
public class WorkDay implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer wid;
    private Integer did;
    private Integer worktime;
    private String ampm;
    private Integer nsnum;
    private String state;
}
