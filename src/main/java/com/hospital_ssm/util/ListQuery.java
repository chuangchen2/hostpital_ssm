package com.hospital_ssm.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ListQuery {
    private Integer page;
    private Integer limit;
    private String sort;
    private String name;
}
