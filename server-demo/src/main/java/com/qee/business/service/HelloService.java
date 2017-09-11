package com.qee.business.service;

import com.qee.business.annotation.Command;
import com.qee.business.annotation.Module;
import com.qee.business.constant.CommandTypeEnum;
import com.qee.business.constant.ModuleTypeEnum;
import com.qee.business.test.User;

/**
 * Created by zhuqi on 2017/9/10.
 */

@Module(moduleId = ModuleTypeEnum.HelloService)
public interface HelloService {

    @Command(cmdId = CommandTypeEnum.consoleHello)
    void consoleHello(String name);

    @Command(cmdId = CommandTypeEnum.defaultHello)
    void defaultHello();

    @Command(cmdId = CommandTypeEnum.userHello)
    void userHello(String teacherId, User user);
}
