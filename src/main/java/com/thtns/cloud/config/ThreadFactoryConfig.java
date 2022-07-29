package com.thtns.cloud.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
public class ThreadFactoryConfig {


    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

        ThreadFactory threadFactory = new ThreadFactoryBuilder().build();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(2 * CORE_POOL_SIZE);
        threadPoolTaskExecutor.setThreadFactory(threadFactory);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setQueueCapacity(256);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return threadPoolTaskExecutor;

    }


}
