package com.qee.api.model;

/**
 * Created by zhuqi on 2017/9/9.
 */
public class Response {

    private boolean success = true;

    private Object data;

    private String reason;

    private int code = 200;

    private Response() {
    }

    private Response(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public Object getData() {
        return data;
    }


    public boolean isSuccess() {
        return success;
    }


    public static Response wrapSuccess(Object data) {
        Response response = new Response(true, data);
        return response;
    }

    public static Response wrapError(String reason) {
        Response response = new Response();
        response.reason = reason;
        response.code = 400;
        return response;
    }
}
