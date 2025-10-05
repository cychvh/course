package com.gec.util;

public enum BusinessExceptionCode {

    USER_LOGIN_ERROR("登录名或密码不对"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
