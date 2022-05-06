package com.hospital_ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Apply {
    @TableId(type = IdType.AUTO)
    private Integer aid;
    @TableField(exist = false)
    private Integer did;
    @TableField(exist = false)
    private String dname;
    @TableField(exist = false)
    private String office;
    private Integer wid;
    @TableField(exist = false)
    private String worktime;
    @TableField(exist = false)
    private String ampm;
    private String reason;
    @TableField("applytime")
    private Timestamp applyTime;
    private String state;
    private String request;
}
