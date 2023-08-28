package com.aixu.async.config.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 冠旭
 * @Date 2023/8/28 10:21
 */
public class CustomThreadFactory implements ThreadFactory {
    private final  AtomicInteger i = new AtomicInteger();

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("tykc-thread-" + i.getAndIncrement());
        return thread;
    }
}
