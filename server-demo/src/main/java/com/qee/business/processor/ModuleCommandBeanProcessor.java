package com.qee.business.processor;

import com.qee.business.annotation.Command;
import com.qee.business.annotation.Module;
import com.qee.business.invoke.ModuleCmdInvoker;
import com.qee.business.invoke.ModuleCmdInvokerManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zhuqi on 2017/9/9.
 */

@Component
public class ModuleCommandBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Class<?>[] interfaces = beanClass.getInterfaces();

        for (Class<?> inter : interfaces) {
            Module moduleAnotation = inter.getAnnotation(Module.class);
            if (moduleAnotation == null) {
                continue;
            }

            Method[] methods = inter.getMethods();
            if (methods == null) {
                continue;
            }

            for (Method method : methods) {
                Command commandAnnotation = method.getAnnotation(Command.class);
                if (commandAnnotation == null) {
                    continue;
                }

                ModuleCmdInvokerManager.addInvoker(moduleAnotation.moduleId().getValue(), commandAnnotation.cmdId().getValue(), ModuleCmdInvoker.valueOf(bean, method));

            }

        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
