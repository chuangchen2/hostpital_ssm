package com.hospital_ssm.util;

public enum HttpCodeUtil {

    OK("20000"), UNAUTH("20001"), FAILED("60024"), EXCEPTION("60025");

    private final String code;

    private HttpCodeUtil(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
