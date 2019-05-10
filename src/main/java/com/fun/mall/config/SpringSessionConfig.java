package com.fun.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 15:16 2019/5/10
 * @ Description：Spring Session配置
 * @ Modified By：
 * @ Version    : 1.0.0$
 */
@Configuration
public class SpringSessionConfig {

    /**
     * Spring session(redis存储方式)监听导致创建大量redisMessageListenerContailner-X线程
     * 为spring session添加springSessionRedisTaskExecutor线程池。
     */
    @Bean
    public ThreadPoolTaskExecutor springSessionRedisTaskExecutor(){
        ThreadPoolTaskExecutor springSessionRedisTaskExecutor = new ThreadPoolTaskExecutor();
        springSessionRedisTaskExecutor.setCorePoolSize(8);
        springSessionRedisTaskExecutor.setMaxPoolSize(16);
        springSessionRedisTaskExecutor.setKeepAliveSeconds(10);
        springSessionRedisTaskExecutor.setQueueCapacity(1000);
        springSessionRedisTaskExecutor.setThreadNamePrefix("Spring session redis executor thread: ");
        return springSessionRedisTaskExecutor;
    }

}
