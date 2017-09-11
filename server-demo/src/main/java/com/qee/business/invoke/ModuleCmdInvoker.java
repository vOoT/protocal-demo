package com.qee.business.invoke;


import com.qee.api.model.Response;

import java.lang.reflect.Method;

/**
 * Created by zhuqi on 2017/9/9.
 */
public class ModuleCmdInvoker {

    private Object bean;

    private Method method;

    private ModuleCmdInvoker() {

    }

    public ModuleCmdInvoker(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    public static ModuleCmdInvoker valueOf(Object bean, Method method) {
        return new ModuleCmdInvoker(bean, method);
    }

    public Response invoke(Object[] args) {
        try {
            Object result = method.invoke(bean, args);
            return Response.wrapSuccess(result);
        } catch (Exception e) {
            return Response.wrapError(e.getMessage());
        }

    }

}
