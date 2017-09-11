package com.qee.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhuqi on 2017/9/10.
 */
@Data
public class RequestParam implements Serializable {
    private static final long serialVersionUID = 1315930105534880644L;


    /**
     * 第几个参数
     */
    private int index;

    /**
     * 参数值
     */
    private Object value;

    /**
     * 是否是一个对象类型
     */
    private int complex;

    /**
     * 如果是复合类型，复合类型是什么
     */
    private Class clazz;

    public RequestParam() {
    }

    public RequestParam(int index, Object value, int complex) {
        this.index = index;
        this.value = value;
        this.complex = complex;
    }

    public RequestParam(int index, Object value, int complex, Class clazz) {
        this.index = index;
        this.value = value;
        this.complex = complex;
        this.clazz = clazz;
    }
}
