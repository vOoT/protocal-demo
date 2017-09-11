package com.qee.business.annotation;

import com.qee.business.constant.ModuleTypeEnum;

import java.lang.annotation.*;

/**
 * Created by zhuqi on 2017/9/9.
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {

    ModuleTypeEnum moduleId();

}
