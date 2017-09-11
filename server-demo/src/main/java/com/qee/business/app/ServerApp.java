package com.qee.business.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by zhuqi on 2017/9/10.
 */
@ComponentScan(basePackages = "com.qee.business")
@EnableAutoConfiguration
@EnableAsync
public class ServerApp {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ServerApp.class, args);
        System.out.println("the main Thread :" + Thread.currentThread().getName());
/*
        HelloService helloService = (HelloService) ApplicationContextUtils.getBean("helloService");
        HelloService helloService1 = ApplicationContextUtils.getBean("helloService", HelloService.class);

        helloService.consoleHello("hello world");
        helloService1.defaultHello();*/
    }
}
