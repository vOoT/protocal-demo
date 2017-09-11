package com.qee.business.service.impl;

import com.qee.business.service.HelloService;
import com.qee.business.test.User;
import org.springframework.stereotype.Service;

/**
 * Created by zhuqi on 2017/9/10.
 */

@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public void consoleHello(String name) {
        System.out.println(name);
    }

    @Override
    public void defaultHello() {
        System.out.println("default hello world");
    }

    @Override
    public void userHello(String teacherId, User user) {
        System.out.println("the teacher id is " + teacherId + " , and user info is" + user.getName() + "  " + user.getId());
    }
}
