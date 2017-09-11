package com.qee.business.service.impl;


import com.qee.api.model.Response;
import com.qee.business.service.session.Session;
import io.netty.channel.Channel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Iterator;

/**
 * Created by zhuqi on 2017/9/10.
 */
public class DefaultSessionImpl implements Session {

    private Channel channel;

    private short moduleId;

    private short commandId;

    private Object[] args;

    public DefaultSessionImpl(Channel channel) {
        this.channel = channel;
    }

    public DefaultSessionImpl(Channel channel, Object args) {
        this.channel = channel;
        if (args instanceof String) {
            try {
                this.args = parseJSON(args);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public short getModuleId() {
        return moduleId;
    }

    @Override
    public short getCommandId() {
        return commandId;
    }

    public void setModuleCmd(short moduleId, short commandId) {
        this.moduleId = moduleId;
        this.commandId = commandId;
    }

    @Override
    public Object[] getArgument() {
        return args;
    }

    @Override
    public void write(Response response) {
        channel.writeAndFlush(response);
    }


    private Object[] parseJSON(Object obj) throws ClassNotFoundException {
        if (!(obj instanceof String)) {
            return null;
        }
        String json = (String) obj;
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray params = (JSONArray) jsonObject.get("params");
        int size = params.size();
        if (size <= 0) {
            return null;
        }
        Object[] ret = new Object[size];
        //params
        Iterator<JSONObject> iterator = params.iterator();
        while (iterator.hasNext()) {
            JSONObject jObj = iterator.next();
            int index = jObj.getInt("index");
            int complex = jObj.getInt("complex");
            if (complex == 0) {
                ret[index - 1] = jObj.get("value");
            } else {
                String strClass = jObj.getString("clazz");
                Class<?> clazz = Class.forName(strClass);
                Object value = jObj.get("value");
                ret[index - 1] = JSONObject.toBean((JSONObject) value, clazz);
            }
        }
        return ret;

    }
}
