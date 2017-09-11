package com.qee.business.test;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhuqi on 2017/9/10.
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1900640319306620353L;

    private Integer id;


    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
