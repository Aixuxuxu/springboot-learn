package com.aixu.async.config;

import com.aixu.async.config.factory.CustomThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 冠旭
 * @Date 2023/8/28 9:49
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {



    @Bean(name = "userInfoThreadPoolExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){

        log.info("---------- 线程池开始加载 ----------");
        int cpuInfo = this.getCpuInfo();
        log.info("线程池核心大小设置为 {}",cpuInfo * 2);
        log.info("线程池最大线程数设置为 {}",cpuInfo * 2 * 2);
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        threadPoolTaskExecutor.setCorePoolSize(cpuInfo * 2);
        // 最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(cpuInfo * 2 * 2);
        // 队列容量
        threadPoolTaskExecutor.setQueueCapacity(30);
        // 活跃时间
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        // 自定义线程工厂
        threadPoolTaskExecutor.setThreadFactory(new CustomThreadFactory());

        // 主线程等待子线程执行时间
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        // 线程名字前缀
//        threadPoolTaskExecutor.setThreadNamePrefix("user-info-");
        // RejectedExecutionHandler:当pool已经达到max-size的时候，如何处理新任务
        // CallerRunsPolicy:不在新线程中执行任务，而是由调用者所在的线程来执行
        // AbortPolicy:谁提交任务谁去执行
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化
        threadPoolTaskExecutor.initialize();
        log.info("---------- 线程池加载完成 ----------");
        return threadPoolTaskExecutor;

    }

    private int getCpuInfo(){
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        int physicalProcessorCount = processor.getPhysicalProcessorCount();
        log.info("CPU 型号 {}" , processor.getProcessorIdentifier().getName());
        log.info("CPU 物理核心数 {}" , processor.getPhysicalProcessorCount());
        return physicalProcessorCount;
    }
}
