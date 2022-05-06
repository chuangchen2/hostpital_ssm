package com.hospital_ssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class HospitalSsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalSsmApplication.class, args);
    }

}
