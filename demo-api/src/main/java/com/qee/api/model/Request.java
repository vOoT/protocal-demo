package com.qee.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhuqi on 2017/9/10.
 */
@Data
public class Request implements Serializable {
    private static final long serialVersionUID = -7043569638909869821L;

    private RequestParam[] params;

    public RequestParam[] getParams() {
        return params;
    }

    public void setParams(RequestParam[] params) {
        this.params = params;
    }
}
