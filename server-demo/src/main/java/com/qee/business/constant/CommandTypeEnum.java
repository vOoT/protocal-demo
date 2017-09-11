package com.qee.business.constant;

/**
 * Created by zhuqi on 2017/9/10.
 */
public enum CommandTypeEnum {

    consoleHello((short) 1, "consoleHello"),
    defaultHello((short) 2, "defaultHello"),
    userHello((short) 3, "userHello");

    private short value;

    private String desc;

    CommandTypeEnum(short value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public short getValue() {
        return value;
    }
}
