package com.yc.spring.test3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.yc.spring.test3.system","com.yc.spring.test3.user"})
public class Config {
}
