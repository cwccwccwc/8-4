package com.yc.ycSpring;

import org.ycframework.annotation.YcComponentScan;
import org.ycframework.annotation.YcConfiguration;

@YcConfiguration
@YcComponentScan(basePackages = {"com.yc","com.yc2"})
public class MyConfig {
}
