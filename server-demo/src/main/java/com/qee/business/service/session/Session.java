package com.qee.business.service.session;


import com.qee.api.model.Response;

/**
 * Created by zhuqi on 2017/9/10.
 */
public interface Session {

    short getModuleId();

    short getCommandId();

    Object [] getArgument();

    void write(Response response);


}
