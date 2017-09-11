package com.qee.business.app;

import com.qee.business.server.Server;

/**
 * Created by zhuqi on 2017/9/11.
 */
public class ServerTest {
    public static void main(String[] args) {
        Server server = new Server("127.0.0.1",8888);
        try {
            server.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
