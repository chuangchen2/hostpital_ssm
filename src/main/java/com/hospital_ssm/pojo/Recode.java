package com.hospital_ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recode implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer rid;
    private Integer pid;
    private Integer wid;
    private Integer did;
    private String serialnumber;
    private String visitdate;
    private String visitnoon;
    private String visittime;
    private String ordertime;
    private String state;
}
