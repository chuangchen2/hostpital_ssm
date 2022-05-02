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
public class Doctor {
    @TableId(type = IdType.AUTO)
    private Integer did;
    private String account;
    private String password;
    private String dname;
    private String fee;
    private String gender;
    private String age;
    private String office;
    private String room;
    private String career;
    private String description;
    private String picpath;
}
