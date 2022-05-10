package com.hospital_ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumSource {
    private String serialnumber;
    private String visitdate;
    private String visitnoon;
    private String visittime;
    private String state;
}
