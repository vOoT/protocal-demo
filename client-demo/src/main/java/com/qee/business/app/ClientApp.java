package com.qee.business.app;

import com.qee.business.server.Client;
import com.qee.business.util.ApplicationContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by zhuqi on 2017/9/10.
 */
@ComponentScan(basePackages = "com.qee.business")
@EnableAutoConfiguration
public class ClientApp {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ClientApp.class, args);
        System.out.println("the main Thread :" + Thread.currentThread().getName());
        Client client = (Client) ApplicationContextUtils.getBean("client");
        client.start();
    }
}
