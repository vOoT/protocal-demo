package com.qee.business.constant;

/**
 * Created by zhuqi on 2017/9/10.
 */
public enum ModuleTypeEnum {
    HelloService((short) 1, "helloService");

    private short value;

    private String desc;

    ModuleTypeEnum(short value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public short getValue() {
        return value;
    }
}
