package com.qee.business.annotation;

import com.qee.business.constant.CommandTypeEnum;

import java.lang.annotation.*;

/**
 * Created by zhuqi on 2017/9/9.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    CommandTypeEnum cmdId();
}
