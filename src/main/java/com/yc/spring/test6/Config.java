package com.yc.spring.test6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
@Configuration
@ComponentScan("com.yc.spring.test6")
public class Config {
    @Bean
    public ExecutorService threadPoolExecutor(){
        return new ThreadPoolExecutor(3,5,0,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(100),//使用有界队列
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
