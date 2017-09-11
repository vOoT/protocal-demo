package com.qee.business.invoke;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhuqi on 2017/9/9.
 */
public class ModuleCmdInvokerManager {

    public static Map<Short, Map<Short, ModuleCmdInvoker>> invokerMap = new ConcurrentHashMap<>();

    public static void addInvoker(short module, short cmd, ModuleCmdInvoker invoker) {

        Map<Short, ModuleCmdInvoker> cmdInvokerMap = invokerMap.get(module);
        if (cmdInvokerMap == null) {
            cmdInvokerMap = new HashMap<>();
            cmdInvokerMap.put(cmd, invoker);
            invokerMap.put(module, cmdInvokerMap);
            return;
        }


        ModuleCmdInvoker cmdInvoker = cmdInvokerMap.get(cmd);
        if (cmdInvoker == null) {
            cmdInvokerMap.put(cmd, invoker);
        }

    }

    public static ModuleCmdInvoker getInvoker(short module, short cmd) {
        Map<Short, ModuleCmdInvoker> cmdInvokerMap = invokerMap.get(module);
        if (cmdInvokerMap == null || cmdInvokerMap.get(cmd) == null) {
            throw new RuntimeException("invoke failed ," + module + " " + cmd);
        }
        return cmdInvokerMap.get(cmd);
    }


}
