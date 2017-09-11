package com.qee.business.app;

import com.qee.business.server.Client;
import com.qee.protocal.client.NettyProtocalClient;

/**
 * Created by zhuqi on 2017/9/11.
 */
public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client(6666,"127.0.0.1",8888);
        client.start();
       /* NettyProtocalClient nettyProtocalClient = new NettyProtocalClient("127.0.0.1",8888);
        nettyProtocalClient.connect();*/
    }
}
