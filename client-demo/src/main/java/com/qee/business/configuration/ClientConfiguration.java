package com.qee.business.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by zhuqi on 2017/9/11.
 */
@Configuration
@PropertySource(value = {"classpath:client.properties"})
public class ClientConfiguration {
}
