package com.qee.business.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by zhuqi on 2017/9/10.
 */
public class ClientStart {

    @Autowired
    private Client client;

    public void init() {
        try {
            client.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        client.stop();
    }
}
