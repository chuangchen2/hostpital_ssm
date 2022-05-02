package com.hospital_ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Apply {
    @TableId(type = IdType.AUTO)
    private Integer aid;
    private Integer did;
    private String dname;
    private Integer wid;
    private String reason;
    private String applyTime;
    private String state;
    private String request;
}
