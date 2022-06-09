package com.hospital_ssm.pojo;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Register {
    private String password;
    private String passwordCof;
    private String account;
    private String name;
    private String email;
}
