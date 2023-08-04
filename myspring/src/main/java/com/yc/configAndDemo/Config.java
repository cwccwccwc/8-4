package com.yc.configAndDemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.ycframework.annotation.YcComponentScan;

@Configuration
@ComponentScan(basePackages = "com.yc")
@EnableAspectJAutoProxy
public class Config {
}
