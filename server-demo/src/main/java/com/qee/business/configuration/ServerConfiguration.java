package com.qee.business.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by zhuqi on 2017/9/10.
 */
@Configuration
@PropertySource(value = {"classpath:server.properties"})
public class ServerConfiguration {

}
